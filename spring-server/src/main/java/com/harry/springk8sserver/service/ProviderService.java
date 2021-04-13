package com.harry.springk8sserver.service;

import com.harry.springk8sserver.config.CoreProperties;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service("providerService")
public class ProviderService implements ApplicationListener<WebServerInitializedEvent> {

  @Autowired
  private CoreProperties properties;

  private final String hostName = System.getenv("HOSTNAME");
  private int serverPort;

  @Override
  public void onApplicationEvent(WebServerInitializedEvent event) {
    this.serverPort = event.getWebServer().getPort();
  }

  public String getServer() {
    InetAddress address = null;
    try {
      address = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return String.format("%s当前访问的服务地址：%s : %s , 用户名:%s, 密码:%s, 外部配置属性值:%s, 内部配置属性值:%s",
        hostName,
        address.getHostAddress(),
        this.serverPort,
        properties.getUsername(),
        properties.getPassword(),
        properties.getMessage(),
        properties.getMessage2());
  }
}
