package com.harry.springfeignapi.feign.falback;

import com.google.common.collect.Lists;
import com.harry.springfeignapi.domain.ServiceDomain;
import com.harry.springfeignapi.feign.FeignClientApi;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 调用端熔断/降级
 */
@Data
@Builder
public class ClientFallback implements FeignClientApi {

  private String errorMessage;

  @Override
  public String getServer() {
    return "服务请求失败了=" + errorMessage;
  }

  @Override
  public List<String> getServiceList() {
    return Lists.newArrayList();
  }

  @Override
  public Object getInstance(String name) {
    return null;
  }

  @Override
  public Object getInstanceByName(String name) {
    return null;
  }

  @Override
  public Object postInstance(ServiceDomain domain) {
    return null;
  }
}
