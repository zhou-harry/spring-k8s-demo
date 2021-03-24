package com.harry.springk8sclient.controller;

import com.harry.springfeignapi.feign.FeignClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  @Autowired
  private FeignClientApi feignClientApi;
  /**
   * Feign方式调用依赖服务
   *
   * @return
   */
  @GetMapping("/getServer")
  public String getServer() {
    return feignClientApi.getServer();
  }

}
