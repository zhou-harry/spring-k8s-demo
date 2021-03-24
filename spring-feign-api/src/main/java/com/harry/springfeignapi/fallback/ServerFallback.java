package com.harry.springfeignapi.fallback;


import com.harry.springfeignapi.feign.FeignClientApi;
import org.springframework.stereotype.Component;


@Component
public class ServerFallback implements FeignClientApi {
    @Override
    public String getServer() {
        return "服务请求失败！";
    }
}
