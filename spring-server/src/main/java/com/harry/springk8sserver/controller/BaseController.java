package com.harry.springk8sserver.controller;

import com.harry.springk8sserver.config.CoreProperties;
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
  private CoreProperties properties;

  private final String hostName = System.getenv("HOSTNAME");

  /**
   * 响应使用了ribbon服务的调用
   * @return
   */
  @GetMapping("/")
  public String ping(){
    log.info("ping of {}", hostName);
    return hostName;
  }

  /**
   * 探针检查响应
   * @return
   */
  @GetMapping("/health")
  public String health() {
    return "OK";
  }

  @GetMapping("/service")
  public List<String> getServiceList(){
    return discoveryClient.getServices();
  }

  @GetMapping("/instance")
  public Object getInstance(@RequestParam("name") String name){
    return discoveryClient.getInstances(name);
  }

  @GetMapping("/message")
  public Object getMessage(){
    return properties.getUsername()+"/"+ properties.getPassword()+"/"+properties.getMessage();
  }
}
