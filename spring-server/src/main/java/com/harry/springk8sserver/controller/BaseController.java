package com.harry.springk8sserver.controller;

import com.harry.springfeignapi.domain.ServiceDomain;
import com.harry.springfeignapi.feign.FeignClientApi;
import com.harry.springk8sserver.service.ProviderService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BaseController implements FeignClientApi {

  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private ProviderService providerService;


  @Override
  public String getServer() {
    return providerService.getServer();
  }

  @Override
  public List<String> getServiceList() {
    return discoveryClient.getServices();
  }

  @Override
  public Object getInstance(@RequestParam("name") String name) {
    return discoveryClient.getInstances(name);
  }

  @Override
  public Object postInstance(ServiceDomain domain) {
    return discoveryClient.getInstances(domain.getName());
  }

}
