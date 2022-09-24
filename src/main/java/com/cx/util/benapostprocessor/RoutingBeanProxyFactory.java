package com.cx.util.benapostprocessor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Map;

public class RoutingBeanProxyFactory {
    private final static String DEFAULT_BEAN_NAME = "helloServiceImpl01";

    public static Object createProxy(String name,Class type,Map<String,Object> beans) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(name,beans));
        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {
        private Object targetObject;

        public VersionRoutingMethodInterceptor(String name,Map<String,Object> beans) {
            this.targetObject = beans.get(name);
            if (this.targetObject == null) {
                this.targetObject = beans.get(DEFAULT_BEAN_NAME);
            }
        }

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            return methodInvocation.getMethod().invoke(this.targetObject,methodInvocation.getArguments());
        }
    }
}
