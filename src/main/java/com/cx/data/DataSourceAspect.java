package com.cx.data;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
@Aspect
public class DataSourceAspect {

    @Autowired
    private ChangeDatasourceService changeDatasourceService;

    @Around("@annotation(com.cx.data.TargetDataSource)")
    public Object changeDataSource(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object object = null;
        try {
            MethodSignature signature = (MethodSignature)( proceedingJoinPoint.getSignature());
            Method method = signature.getMethod();
             Annotation[] annotations =   method.getDeclaredAnnotations();
            if (method.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource targetDataSource = method.getDeclaredAnnotation(TargetDataSource.class);
                //String key = ThreadLocalKeyUtil.getDB();
                String key = StringUtils.isEmpty(targetDataSource.value()) ? "my_database_01" : targetDataSource.value();
                changeDatasourceService.changDatasource(key);
                object =proceedingJoinPoint.proceed();
            }
        }catch ( Throwable throwable) {
            throw new Exception("切换数据源失败");
        }
        ThreadLocalUtil.clearDB();
        return object;
    }
}
