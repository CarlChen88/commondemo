package com.cx.util.beanutil;

import com.cx.util.TestService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 加载定制的bean
 */
@Component
public class FactoryBeanUtils implements FactoryBean {
   private Object proxy;
    @Override
    public Object getObject() throws Exception {
        return this.proxy;
    }

    @Override
    public Class<?> getObjectType() {
        if(this.proxy !=null) {
            return this.proxy.getClass();
        }
        return null;
    }
}
