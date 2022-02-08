package com.wyp.juc;

import java.util.concurrent.TimeUnit;

/**
 * 验证 volatile 的可见性
 * 1.1 假如 int num = 0; num 变量之前没有添加 volatile 关键字修饰，没有可见性
 * 1.2 添加了 volatile 可以解决可见性
 *
 * 验证 volatile 不保证原子性
 * 2.1 原子性是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体的业务时，中间不可以被其他线程加塞或者被分割。需要整体完成，要么同时成功，要么同时失败
 */
public class volatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少？ 预期结果是 20 * 10000 ，实际执行的结果不是
        // try {
        //     TimeUnit.SECONDS.sleep(5);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        // Thread.activeCount()返回活动线程的当前线程的线程组中的数量。
        while (Thread.activeCount() > 2) {
            // Thread.yield() 方法，使当前线程由执行状态，变成为就绪状态，让出cpu时间，在下一个线程执行时候，此线程有可能被执行，也有可能没有被执行。
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally num value : " + myData.num);
    }

    // volatile 可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
    private static void seeOKByVolatile() {
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

    //此时num前面加了 volatile 关键字修改的，volatile 不保证原子性
    public void addPlusPlus() {
        this.num++;
    }


}