package com.star.twschool.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping(value = "/info",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String hello(@RequestParam String id) {
        return "hello ".concat(id);
    }

}
