package com.wyp.juc.waitAndNotify;

class MyData{
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        //1、判断
        while (num > 0){//多线程交互中判断条件使用if会存在虚假唤醒，需要使用while
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
        while (num == 0){
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
 * 2    判断/操作共享变量/通知
 * 3    多线程交互中，必须防止多线程的虚假唤醒，也即（判断只能用while，不能使用if）
 */
public class ThreadWaitAndNotifyDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int k = 0; k < 3; k++) {
            new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    try {
                        myData.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"A"+k).start();
        }

        for (int k = 0; k < 3; k++) {
            new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    try {
                        myData.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"B"+k).start();
        }

    }

}
