package com.harry.springfeignapi.feign;

import com.harry.springfeignapi.fallback.ServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "spring-k8s-server", fallback = ServerFallback.class)
public interface FeignClientApi {

    @GetMapping("/server")
    String getServer();
}
