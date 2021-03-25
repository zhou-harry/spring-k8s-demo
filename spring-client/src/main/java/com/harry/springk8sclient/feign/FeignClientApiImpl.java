package com.harry.springk8sclient.feign;

import com.harry.springfeignapi.feign.FeignClientApi;
import com.harry.springk8sclient.feign.falback.ClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "${provider.service.name}",
    url = "${provider.service.name}",
//    fallbackFactory = ClientFallbackFactory.class
    fallback = ClientFallback.class
)
public interface FeignClientApiImpl extends FeignClientApi {

}
