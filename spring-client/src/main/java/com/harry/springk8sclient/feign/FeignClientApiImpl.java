package com.harry.springk8sclient.feign;

import com.harry.springfeignapi.feign.FeignClientApi;
import com.harry.springk8sclient.feign.falback.ClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "http://spring-k8s-server-service:8080",
    url = "http://spring-k8s-server-service:8080",
    fallback = ClientFallback.class
)
public interface FeignClientApiImpl extends FeignClientApi {

}
