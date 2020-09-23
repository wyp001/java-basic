package com.wyp.dynamicProxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }
    //proxy 代理对象
    //method 要实现的方法
    //args 方法的参数    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行之前："+method.getName());
        Object invoke = method.invoke(object, args);
        System.out.println("代理执行之后："+method.getName());
        return invoke;
    }
}