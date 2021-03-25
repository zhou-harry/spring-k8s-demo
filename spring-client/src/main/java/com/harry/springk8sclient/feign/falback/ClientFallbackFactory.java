package com.harry.springk8sclient.feign.falback;

import com.harry.springk8sclient.feign.FeignClientApiImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientFallbackFactory implements FallbackFactory<FeignClientApiImpl> {

  @Autowired
  private ClientFallback fallback;
  @Override
  public FeignClientApiImpl create(Throwable cause) {
    log.error("远程调用失败："+cause.getMessage());
    return fallback;
  }
}
