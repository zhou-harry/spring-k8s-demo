package com.harry.springk8sclient.feign.falback;

import com.google.common.collect.Lists;
import com.harry.springfeignapi.domain.ServiceDomain;
import com.harry.springk8sclient.feign.FeignClientApiImpl;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 调用端熔断/降级
 */
@Data
@Builder
public class ClientFallback implements FeignClientApiImpl {

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
  public Object postInstance(ServiceDomain domain) {
    return null;
  }
}
