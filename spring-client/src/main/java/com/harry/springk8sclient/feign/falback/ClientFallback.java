package com.harry.springk8sclient.feign.falback;

import com.harry.springfeignapi.fallback.ServerFallback;
import org.springframework.stereotype.Component;

@Component
public class ClientFallback implements ServerFallback {
  @Override
  public String getServer() {
    return "服务请求失败了============";
  }
}
