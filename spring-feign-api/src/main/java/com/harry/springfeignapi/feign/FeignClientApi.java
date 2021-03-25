package com.harry.springfeignapi.feign;

import org.springframework.web.bind.annotation.GetMapping;

public interface FeignClientApi {

    @GetMapping("/server")
    String getServer();
}
