package com.harry.springk8sserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
  @Autowired
  private DiscoveryClient discoveryClient;

  @GetMapping("/service")
  public List<String> getServiceList(){
    return discoveryClient.getServices();
  }

  @GetMapping("/instance")
  public Object getInstance(@RequestParam("name") String name){
    return discoveryClient.getInstances(name);
  }
}
