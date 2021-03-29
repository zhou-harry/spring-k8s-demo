package com.harry.springk8sclient.controller;

import com.harry.springfeignapi.domain.ServiceDomain;
import com.harry.springfeignapi.feign.FeignClientApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class ClientController {

  private final static Random random = new Random();
  @Autowired
  private FeignClientApi feignClientApi;

  @GetMapping("/test")
  public String test() {
    return "OK";
  }

  /**
   * Feign方式调用依赖服务
   *
   * @return
   */
  @GetMapping("/server")
  public String getServer() {
    return feignClientApi.getServer();
  }

  @GetMapping("/service")
  public List<String> getServiceList() {
    return feignClientApi.getServiceList();
  }

  @HystrixCommand(
      commandProperties = { // Command 熔断配置
          // 设置操作时间为 100 毫秒
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
      },
      fallbackMethod = "getInstanceFallback" // 设置 fallback 方法
  )
  @GetMapping("/instance")
  public Object getInstance(@RequestParam("name") String name) throws InterruptedException {
    long executeTime = random.nextInt(200);
    // 通过休眠来模拟执行时间
    log.info("Find Execute Time : " + executeTime + " ms");
    Thread.sleep(executeTime);
    return feignClientApi.getInstance(name);
  }

  @GetMapping("/instance2/{serviceName}")
  public Object getInstance2(@PathVariable("serviceName") String serviceName) {
    return feignClientApi.getInstanceByName(serviceName);
  }

  @PostMapping("/instance3")
  public Object getInstance3(@RequestBody ServiceDomain domain) {
    return feignClientApi.postInstance(domain);
  }

  public Object getInstanceFallback(String name) {
    return name+"，执行时间过长，触发熔断......";
  }
}
