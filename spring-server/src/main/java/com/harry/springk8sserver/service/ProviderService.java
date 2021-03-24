package com.harry.springk8sserver.service;

import com.harry.springfeignapi.feign.FeignClientApi;
import com.harry.springk8sserver.config.CoreProperties;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service("providerService")
public class ProviderService implements FeignClientApi,
    ApplicationListener<WebServerInitializedEvent> {

  @Autowired
  private CoreProperties properties;

  private final String hostName = System.getenv("HOSTNAME");
  private int serverPort;

  @Override
  public void onApplicationEvent(WebServerInitializedEvent event) {
    this.serverPort = event.getWebServer().getPort();
  }

  @Override
  public String getServer() {
    InetAddress address = null;
    try {
      address = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return hostName+"当前访问的服务地址：" + address.getHostAddress() + ":" + this.serverPort + ", 用户名:" + properties
        .getUsername() + ", 密码:" + properties.getPassword()+"/"+properties.getMessage();
  }
}
