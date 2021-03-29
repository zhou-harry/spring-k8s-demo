package com.harry.springk8sserver.controller;

import com.harry.springfeignapi.domain.ServiceDomain;
import com.harry.springfeignapi.feign.FeignClientApi;
import com.harry.springk8sserver.service.ProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "Feign调用测试接口",tags ="Feign调用测试接口")
public class BaseController implements FeignClientApi {

  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private ProviderService providerService;


  @Override
  @ApiOperation(value = "获取服务提供者信息")
  @GetMapping("/server")
  public String getServer() {
    return providerService.getServer();
  }

  @Override
  @ApiOperation(value = "获取服务列表信息")
  @GetMapping("/service")
  public List<String> getServiceList() {
    return discoveryClient.getServices();
  }

  @Override
  @ApiOperation(value = "根据参数名称获取服务信息")
  @GetMapping("/instance")
  public Object getInstance(String name) {
    return discoveryClient.getInstances(name);
  }

  @Override
  @ApiOperation(value = "根据变量名称获取服务信息")
  @GetMapping("/instance2/{name}")
  public Object getInstanceByName(String name) {
    return discoveryClient.getInstances(name);
  }

  @Override
  @ApiOperation(value = "根据参数对象获取服务信息")
  @PostMapping("/instance3")
  public Object postInstance(ServiceDomain domain) {
    return discoveryClient.getInstances(domain.getName());
  }

}
