package com.harry.springk8sclient;

import com.harry.springfeignapi.feign.FeignClientApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = FeignClientApi.class)
public class ClientApp {

  public static void main(String[] args) {
    SpringApplication.run(ClientApp.class, args);
  }
}
