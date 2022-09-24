package com.cx.aspect.model02;

import com.cx.aspect.model01.TargetDatasource;
import com.cx.service.extend.DService;
import com.cx.util.beanutil.BeanFactoryUtils;
import com.cx.util.beanutil.SpringBeanUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

public class MyMethodIntercepter implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        TargetDatasource targetDatasource = AnnotationUtils.getAnnotation(method,TargetDatasource.class);
        System.out.println(targetDatasource.database()+":"+targetDatasource.value());
        DService dService = SpringBeanUtils.getBeanByClassName(DService.class);
        dService.test1();
        DService dService1 = BeanFactoryUtils.getBeanByClassName(DService.class);
        dService1.test1();
        return methodInvocation.proceed();
    }
}
