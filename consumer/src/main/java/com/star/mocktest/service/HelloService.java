package com.star.mocktest.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider")
public interface HelloService {

    @GetMapping(value = "/info")
    String hello(@RequestParam String id);

}
