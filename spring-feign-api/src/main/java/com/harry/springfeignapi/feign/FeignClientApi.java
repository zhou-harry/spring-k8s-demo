package com.harry.springfeignapi.feign;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface FeignClientApi {

  @GetMapping("server")
  String getServer();

  @GetMapping("/service")
  List<String> getServiceList();

  @GetMapping("/instance")
  Object getInstance(@RequestParam("name") String name);
}
