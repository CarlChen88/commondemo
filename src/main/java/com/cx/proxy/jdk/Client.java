package com.cx.proxy.jdk;

import com.cx.proxy.common.RealSubject;
import com.cx.proxy.common.Subject;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));
        subject.sayHello();
    }
}
