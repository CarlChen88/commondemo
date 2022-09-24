package com.cx.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;

//@Component
public class Cat implements InstantiationAwareBeanPostProcessor, InitializingBean, BeanFactoryAware, BeanFactoryPostProcessor, BeanPostProcessor, BeanNameAware, DisposableBean {

    public Cat() {
        System.out.println("1==========================Cat构造器");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("4==========================【Cat-->InitializingBean】接口");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3==========================【BeanFactoryAware】接口调用setBeanFactory()方法");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("5==========================【BeanFactoryPostProcessor】接口的postProcessBeanFactory方法");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("6==========================【BeanPostProcessor】接口实现类的postProcessBeforeInitialization方法 beanName:" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("7==========================【BeanPostProcessor】接口实现类的postProcessAfterInitialization()方法 beanName:" + beanName);
        return bean;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("2==========================【BeanNameAware】调用接口的setBeanName()方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("8=========================【DisposableBean】接口调用destory()方法");
    }


    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("实例化之前========InstantiationAwareBeanPostProcessor============ beanName:"+beanName);
        return null;
    }

    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("实例化之后==========InstantiationAwareBeanPostProcessor========== beanName:"+beanName);
        return true;
    }


    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("属性填充========InstantiationAwareBeanPostProcessor====");
        return pvs;
    }


}
