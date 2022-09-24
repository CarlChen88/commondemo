package com.cx.aspect.model02;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAdvisor {

    @Bean
    public AspectJExpressionPointcutAdvisor setPointcut(){
        AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        aspectJExpressionPointcutAdvisor.setExpression("@annotation(com.cx.aspect.model01.TargetDatasource)");
        aspectJExpressionPointcutAdvisor.setAdvice(new MyMethodIntercepter());
        return aspectJExpressionPointcutAdvisor;
    }
}
