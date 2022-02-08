package com.wyp.juc;

import java.util.concurrent.TimeUnit;

/**
 * 验证 volatile 的可见性
 * 假如 int num = 0; num 变量之前没有添加 volatile 关键字修饰，没有可见性
 */
public class volatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        while (myData.num == 0) {
            // main 线程一直在这里循环，直到num值不为 0
        }
        System.out.println(Thread.currentThread().getName()+ "\t mission is over");
    }

}

class MyData {

    // int num = 0; // 不添加 volatile 关键字 主线程 一直卡在 while循环处，下边的print语句不会被执行
    volatile int num = 0; // 添加 volatile 关键字 主线程 while循环下边的print语句会被执行

    public void addTo60(){
        this.num = 60;
    }
}