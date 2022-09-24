package com.cx.controller;

import com.cx.util.benapostprocessor.HelloService;
import com.cx.util.benapostprocessor.RountingInjected;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class TestController2 {

    @RountingInjected("helloServiceImpl02")
    private HelloService helloService;

    @GetMapping("/test")
    public String test() {
        helloService.sayHello2("sssssssssssssssss");
        return "success";
    }
}
