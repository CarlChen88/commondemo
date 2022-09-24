package com.cx.util.benapostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


//@Component
public class BeanFactoryPostProcessorService implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        HelloServiceImpl01 helloServiceImpl01 = (HelloServiceImpl01)configurableListableBeanFactory.getBean("helloServiceImpl01");
        helloServiceImpl01.sayHello();
    }
}
