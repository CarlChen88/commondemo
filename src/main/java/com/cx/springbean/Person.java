package com.cx.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

//@Component
public class Person implements BeanNameAware , InitializingBean ,DisposableBean {
    private String name;

    private String beanName;

    private BeanFactory beanFactory;


    public Person() {
        System.out.println("加载Person的构造器！");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("【BeanNameAware】调用接口的setBeanName()方法");
        this.beanName = s;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【InitializingBean】接口调用afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【DisposableBean】接口调用destory()方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
