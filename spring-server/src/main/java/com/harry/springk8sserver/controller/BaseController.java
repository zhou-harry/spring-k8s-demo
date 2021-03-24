package com.harry.springk8sserver.controller;

import com.harry.springk8sserver.service.ProviderService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BaseController {

  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private ProviderService providerService;


  @GetMapping("server")
  public String getServer() {
    return providerService.getServer();
  }

  @GetMapping("/service")
  public List<String> getServiceList() {
    return discoveryClient.getServices();
  }

  @GetMapping("/instance")
  public Object getInstance(@RequestParam("name") String name) {
    return discoveryClient.getInstances(name);
  }

}
