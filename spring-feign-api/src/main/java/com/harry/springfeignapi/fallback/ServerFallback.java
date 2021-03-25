package com.harry.springfeignapi.fallback;


import com.harry.springfeignapi.feign.FeignClientApi;


public class ServerFallback implements FeignClientApi {

  @Override
  public String getServer() {
    return "服务端异常";
  }
}
