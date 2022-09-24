package com.cx.proxy.common;

public class RealSubject implements Subject {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}
