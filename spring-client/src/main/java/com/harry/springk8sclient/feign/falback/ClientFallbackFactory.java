package com.harry.springk8sclient.feign.falback;

import com.harry.springk8sclient.feign.FeignClientApiImpl;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 调用端熔断/降级工厂
 */
@Slf4j
@Component
public class ClientFallbackFactory implements FallbackFactory<FeignClientApiImpl> {

  @Override
  public FeignClientApiImpl create(Throwable cause) {
    // TODO: 2021/3/25 后台打印跟踪日志
    log.error("远程调用失败：" + cause.getMessage());

    return ClientFallback.builder()
        .errorMessage("远程调用失败：" + cause.getMessage())
        .build();
  }
}
