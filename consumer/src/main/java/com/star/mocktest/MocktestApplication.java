package com.star.mocktest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
public class MocktestApplication {

    @Autowired
    private RestTemplateBuilder builder;

    public static void main(String[] args) {
        SpringApplication.run(MocktestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

}
