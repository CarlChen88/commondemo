package com.cx.springbean;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmartInitializingSingletonDemo implements SmartInitializingSingleton {
    @Autowired
    private Annimal annimal;
    @Override
    public void afterSingletonsInstantiated() {
        annimal.test();
    }
}
