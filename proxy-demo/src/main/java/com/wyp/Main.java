package com.wyp;

import com.wyp.common.RealSell;
import com.wyp.common.Sell;
import com.wyp.dynamicProxy.cglib.CGRoom;
import com.wyp.dynamicProxy.cglib.MyMethodInterceptor;
import com.wyp.dynamicProxy.jdk.ProxyHandler;
import com.wyp.staticProxy.ProxyClass;
import com.wyp.common.RealRoom;
import com.wyp.common.Room;
import com.wyp.staticProxy.ProxyClass2;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {

    @Test
    public void staticProxyTest() {
        RealRoom realRoom =new RealRoom("碧桂园");
        ProxyClass proxyClass=new ProxyClass(realRoom);
        proxyClass.rent();
        //--------------------------------
        RealSell realSell =new RealSell();
        ProxyClass2 proxyClass2=new ProxyClass2(realSell);
        proxyClass2.sellRoom();
    }


    @Test
    public void jdkDynamicProxy(){
        Room room=new RealRoom("碧桂园");
        /**
         *  obj.getClass().getClassLoader()类加载器
         *  obj.getClass().getInterfaces() 目标类实现的接口
         *  InvocationHandler对象
         */
        InvocationHandler invocationHandler=new ProxyHandler(room);
        Room proxyRoom = (Room) Proxy.newProxyInstance(room.getClass().getClassLoader(), room.getClass().getInterfaces(), invocationHandler);
        proxyRoom.rent();
        //--------------------------------
        /**
         * 通过和staticProxyTest对比发现
         *  ①静态代理需要为每一个被代理的对象创建一个代理类，代理类和被代理对象实现统一个代理接口，如：
         *      ProxyClass代理RealRoom，这两个类共同实现Room的接口
         *  ②动态代理只需要写一个代理类，可以供对个被代理的对象使用
         */
        Sell sell=new RealSell();
        InvocationHandler ih2=new ProxyHandler(sell);
        Sell proxySell = (Sell) Proxy.newProxyInstance(sell.getClass().getClassLoader(), sell.getClass().getInterfaces(), ih2);
        proxySell.sellRoom();
    }

    @Test
    public void cglibDynamicProxyTest(){
        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer=new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(CGRoom.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());
        //创建代理对象
        CGRoom proxy= (CGRoom) enhancer.create();
        proxy.rent("碧桂园");

    }





}