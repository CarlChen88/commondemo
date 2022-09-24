package com.cx.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

//@Component
public class MyBeanFactoryAware implements BeanFactoryAware {

    private static  BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryAware】接口调用setBeanFactory()方法");
        this.beanFactory = beanFactory;
    }

    public static <T> T getBean(Class<T> className){
        return beanFactory.getBean(className);
    }

    public static Object getBeanByName(String beanName) {
        return beanFactory.getBean(beanName);
    }
}
