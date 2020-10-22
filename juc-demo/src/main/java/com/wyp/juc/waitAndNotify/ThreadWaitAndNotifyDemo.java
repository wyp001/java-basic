package com.wyp.juc.waitAndNotify;

class MyData{
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        //1、判断
        if(num > 0){
            this.wait();
        }
        //2、 操作共享变量
        num++;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //3、通知其他线程
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //1、判断
        if(num == 0){
            this.wait();
        }
        //2、 操作共享变量
        num--;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //3、通知其他线程
        this.notifyAll();
    }
}

/**
 * 题目：
 * 现在两个线程，可以操作初始值为零的一个变量,实现一个线程对该变量加，一个线程对该变量减1,
 * A生产一个，B消费一个，B消费完后，A再生产
 * 实现交替,来1e轮,变量初始值为零。
 * 1    高内聚低耦合前提下，线程操作资源类
 */
public class ThreadWaitAndNotifyDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    myData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    myData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }

}
