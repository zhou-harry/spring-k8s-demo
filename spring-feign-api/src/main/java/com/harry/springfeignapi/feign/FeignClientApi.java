package com.harry.springfeignapi.feign;

import com.harry.springfeignapi.domain.ServiceDomain;
import com.harry.springfeignapi.feign.falback.ClientFallbackFactory;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${provider.service.name}",
        url = "${provider.service.name}",
        fallbackFactory = ClientFallbackFactory.class
)
public interface FeignClientApi {

  @GetMapping("/server")
  String getServer();

  @GetMapping("/service")
  List<String> getServiceList();

  @GetMapping("/instance")
  Object getInstance(@RequestParam("name") String name);

  @GetMapping("/instance2/{name}")
  Object getInstanceByName(@PathVariable("name") String name);

  @PostMapping("/instance3")
  Object postInstance(@RequestBody ServiceDomain domain);
}
