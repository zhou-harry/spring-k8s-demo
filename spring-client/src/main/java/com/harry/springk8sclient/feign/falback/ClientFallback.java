package com.harry.springk8sclient.feign.falback;

import com.harry.springk8sclient.feign.FeignClientApiImpl;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
public class ClientFallback implements FeignClientApiImpl {
  private String errorMessage;
  @Override
  public String getServer() {
    return "服务请求失败了============";
  }
}
