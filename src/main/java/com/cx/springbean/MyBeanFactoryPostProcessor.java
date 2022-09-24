package com.cx.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Person person = (Person)configurableListableBeanFactory.getBean("person");
        person.setName("chenxiang");
        System.out.println("【BeanFactoryPostProcessor】接口的postProcessBeanFactory方法");
    }
}
