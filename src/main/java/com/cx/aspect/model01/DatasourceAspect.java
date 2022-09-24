package com.cx.aspect.model01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(-1)
public class DatasourceAspect {

    @Pointcut("@annotation(com.cx.aspect.model01.TargetDatasource)")
    public void changeDatasource(){ }

    @Around("changeDatasource()")
    public Object changeAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            Method method = ((MethodSignature) signature).getMethod();
            Class<?> declaringClass = method.getDeclaringClass();
            System.out.println(declaringClass.getName());
            System.out.println(declaringClass.getCanonicalName());
            System.out.println(declaringClass.getSimpleName());
            System.out.println(declaringClass.getTypeName());
            if(method.isAnnotationPresent(TargetDatasource.class)) {
                TargetDatasource tt = AnnotationUtils.getAnnotation(method,TargetDatasource.class);
                System.out.println(tt.database()+":"+tt.value());
            }
        }
        return  proceedingJoinPoint.proceed();
    }
}
