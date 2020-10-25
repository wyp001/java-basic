package com.wyp.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 需求：
 * 有六个线程，6个线程都执行完成时，再执行main方法
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        int n = 6;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t执行完成");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //每个线程执行到这里时进入等待，当线程的数量减少到0时（即创建的线程的任务都执行完时）才继续向下执行
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t主线程执行完成");
    }

}
