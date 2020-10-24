package com.wyp.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

/**
 * Callable和Runnable接口的区别
 * 1、Callable有返回值
 * 2、Callable接口的call方法抛出异常
 * 3、实现的方法不同，Callable接口实现call(),Runnable接口实现run()
 */
class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("---------exec MyThread2 call()-----------");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}


public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(new MyThread2());//FutureTask是Runnable接口的实现类
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        System.out.println("--------main计算完成----------");

        System.out.println(futureTask.get());
        /**启动A，B两个线程去执行call，结果却只执行了一次call()方法体，
         * 因为A，B两个线程调用的是同一futureTask对象，futureTask的结果会复用
         * 输出结果：
         * --------main计算完成----------
         * ---------exec MyThread2 call()-----------
         * 1024
         * 用
         */


    }

}
