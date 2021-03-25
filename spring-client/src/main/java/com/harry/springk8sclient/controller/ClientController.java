package com.harry.springk8sclient.controller;

import com.harry.springk8sclient.feign.FeignClientApiImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientController {
  private final static Random random = new Random();
  @Autowired
  private FeignClientApiImpl feignClientApi;
  /**
   * Feign方式调用依赖服务
   *
   * @return
   */
  @HystrixCommand(
      commandProperties = { // Command 熔断配置
          // 设置操作时间为 100 毫秒
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
      },
      fallbackMethod = "getServerFallback" // 设置 fallback 方法
  )
  @GetMapping("/server")
  public String getServer() throws InterruptedException {
    long executeTime = random.nextInt(200);
    // 通过休眠来模拟执行时间
    log.info("Find Execute Time : " + executeTime + " ms");
    Thread.sleep(executeTime);

    return feignClientApi.getServer();
  }
  public String getServerFallback(){
    return "出发熔断";
  }
}
