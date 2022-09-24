package com.cx.util.beanutil;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryUtils implements BeanFactoryAware {
    private static BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanFactoryUtils.beanFactory = beanFactory;
    }

    public static  <T> T getBeanByClassName(Class<T> className) {
        return  beanFactory.getBean(className);
    }

    public static <T> T getBeanByBeanName(String beanName) {
        return (T)beanFactory.getBean(beanName);
    }

}
