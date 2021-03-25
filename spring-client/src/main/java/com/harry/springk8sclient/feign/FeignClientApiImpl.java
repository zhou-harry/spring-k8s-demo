package com.harry.springk8sclient.feign;

import com.harry.springk8sclient.feign.falback.ClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    value = "${provider.service.name}",
    url = "${provider.service.name}",
    fallback = ClientFallback.class
)
public interface FeignClientApiImpl {

}
