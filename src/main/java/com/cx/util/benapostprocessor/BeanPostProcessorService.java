package com.cx.util.benapostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

//@Component
public class BeanPostProcessorService implements BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        if("helloServiceImpl02".equals(beanName)) {
            System.out.println("beanName:"+beanName);
        }
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(RountingInjected.class)) {
                if (!field.getType().isInterface()) {
                    throw new BeanCreationException("RountingInjected filed must be declared as an Interface +"+field.getName() + "@Class"+targetClass.getName());
                }
                try {
                    this.handlerRountingInjected(field,bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    private void handlerRountingInjected(Field field, Object object) throws IllegalAccessException {
        Class<Object> type = (Class<Object>) field.getType();
        Map<String,Object> map =  this.applicationContext.getBeansOfType(type);
        field.setAccessible(true);
        if (map.size() == 1) {
            field.set(object,map.values().iterator().next());
        } else {
            String injectVal = field.getAnnotation(RountingInjected.class).value();
            Object objectValue = RoutingBeanProxyFactory.createProxy(injectVal,type,map);
            field.set(object,objectValue);
        }
    }
}
