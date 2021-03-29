package com.harry.springk8sserver;

import com.harry.springcommon.swagger.EnableSwagger;
import com.harry.springk8sserver.config.CoreProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableSwagger
@EnableConfigurationProperties(CoreProperties.class)
public class ServerApp {

  public static void main(String[] args) {
    SpringApplication.run(ServerApp.class, args);
  }
}
