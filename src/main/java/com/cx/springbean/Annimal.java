package com.cx.springbean;

import org.springframework.stereotype.Component;

@Component
public class Annimal {
    public Annimal() {
        System.out.println("Annimal构造器");
    }

    public void test(){
        System.out.println("annimal test");
    }
}
