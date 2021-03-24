package com.harry.springk8sserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "k8s.demo")
public class CoreProperties {

  private String username;
  private String password;
  private String message;
}
