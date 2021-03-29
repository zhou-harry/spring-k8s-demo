package com.harry.springfeignapi.feign;

import com.harry.springfeignapi.domain.ServiceDomain;
import com.harry.springfeignapi.feign.falback.ClientFallbackFactory;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${provider.service.name}",
        url = "${provider.service.name}",
        fallbackFactory = ClientFallbackFactory.class
)
public interface FeignClientApi {


  String getServer();


  List<String> getServiceList();


  Object getInstance(@RequestParam("name") String name);


  Object getInstanceByName(@PathVariable("name") String name);


  Object postInstance(@RequestBody ServiceDomain domain);
}
