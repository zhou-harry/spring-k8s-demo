package com.harry.springk8sclient.feign.falback;

import com.harry.springk8sclient.feign.FeignClientApiImpl;
import org.springframework.stereotype.Component;

@Component
public class ClientFallback implements FeignClientApiImpl {
  @Override
  public String getServer() {
    return "服务请求失败了============";
  }
}
