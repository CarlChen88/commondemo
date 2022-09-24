package com.cx.util.beanutil;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

//@Component
public class DisposableBeanUtils implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("bean destroy==============");
    }
}
