package com.wyp.dynamicProxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
 
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理执行之前："+method.getName());
        Object object=methodProxy.invokeSuper(o,objects);
        System.out.println("代理执行之后："+method.getName());
        return object;
    }
}