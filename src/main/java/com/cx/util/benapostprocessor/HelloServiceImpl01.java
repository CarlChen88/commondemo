package com.cx.util.benapostprocessor;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl01 implements HelloService {

    @Override
    public void sayHello2(String something) {
        System.out.println(this.getClass().getName()+"sayHello1");
    }
}
