package com.cx.springbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBeanTest {
    public static void main(String[] args) {
       /* ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.cx.springbean");
        System.out.println("初始化容器成功！");
        Cat cat = (Cat)applicationContext.getBean("cat");
        System.out.println(cat);
        System.out.println("关闭容器!");
        ((AnnotationConfigApplicationContext) applicationContext).registerShutdownHook();*/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.cx.springbean");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Object bDemo = applicationContext.getBean(ADemo.class);
        System.out.println(bDemo);
    }
}
