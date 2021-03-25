package com.harry.springk8sclient.feign.falback;

import com.harry.springk8sclient.feign.FeignClientApiImpl;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientFallback implements FeignClientApiImpl {

  private String errorMessage;

  @Override
  public String getServer() {
    return "服务请求失败了=" + errorMessage;
  }
}
