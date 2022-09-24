package com.cx.util.benapostprocessor;

public interface HelloService {
    default void sayHello(){
        System.out.println("sayHello");
    }
    void sayHello2(String something);
}
