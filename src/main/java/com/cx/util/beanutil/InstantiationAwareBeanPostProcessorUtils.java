package com.cx.util.beanutil;

import com.cx.controller.TestController;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

@Component
public class InstantiationAwareBeanPostProcessorUtils implements InstantiationAwareBeanPostProcessor {

    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
       if (bean instanceof TestController) {
           TestController testController = (TestController) bean;
           testController.setAstr("aaaaa");
       }
        return true;
    }

     public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return pvs;
    }
}
