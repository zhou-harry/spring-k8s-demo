package com.harry.springk8sserver.controller;

import com.harry.springk8sserver.config.CoreProperties;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BaseController implements ApplicationListener<WebServerInitializedEvent> {
  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private CoreProperties properties;

  private final String hostName = System.getenv("HOSTNAME");
  private int serverPort;
  @Override
  public void onApplicationEvent(WebServerInitializedEvent event) {
    this.serverPort = event.getWebServer().getPort();
  }
  /**
   * 响应使用了ribbon服务的调用
   * @return
   */
  @GetMapping("/server")
  public String getServer(){
    InetAddress address = null;
    try {
      address = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return hostName+"当前访问的服务地址：" + address.getHostAddress() + ":" + this.serverPort + ", 用户名:" + properties
        .getUsername() + ", 密码:" + properties.getPassword()+"/"+properties.getMessage();
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

}
