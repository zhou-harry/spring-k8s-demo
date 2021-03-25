package com.harry.springk8sclient.controller;

import com.harry.springk8sclient.feign.FeignClientApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  @Autowired
  private FeignClientApiImpl feignClientApi;
  /**
   * Feign方式调用依赖服务
   *
   * @return
   */
  @GetMapping("/server")
  public String getServer() {
    return feignClientApi.getServer();
  }

}
