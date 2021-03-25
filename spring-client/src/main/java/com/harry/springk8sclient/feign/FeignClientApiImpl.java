package com.harry.springk8sclient.feign;

import com.harry.springfeignapi.feign.FeignClientApi;
import com.harry.springk8sclient.feign.falback.ClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "http://spring-k8s-server-service:8080",
    url = "http://spring-k8s-server-service:8080",
    fallbackFactory = ClientFallbackFactory.class
)
public interface FeignClientApiImpl extends FeignClientApi {

}
