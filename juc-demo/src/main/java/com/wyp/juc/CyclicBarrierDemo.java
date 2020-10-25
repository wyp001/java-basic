package com.wyp.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 当线程达到指定的线程数量时，开始执行 new Runnable()中的run方法，
 * 可以立即为满人发车
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //public CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("----------召唤神龙--------------");
        });
        for (int i = 1; i <= 7; i++) {
            final int num = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第："+ num + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }



    }


}
