package com.harry.springk8sclient.feign;

import com.harry.springfeignapi.feign.FeignClientApi;
import com.harry.springk8sclient.feign.falback.ClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "${provider.service.name}",
    fallbackFactory = ClientFallbackFactory.class
)
public interface FeignClientApiImpl extends FeignClientApi {

}
