package com.wyp.juc.semaphoreDemo;


/**
 * 【java并发核心一】Semaphore 的使用思路   https://www.cnblogs.com/klbc/p/9500947.html
 *
 */
public class SemaphoreTest {
    public static void main(String args[]) {
        SemaphoreService service = new SemaphoreService();
        for (int i = 0; i < 10; i++) {
            MyThread t = new MyThread("thread" + (i + 1), service);
            t.start();// 这里使用 t.run() 也可以运行，但是不是并发执行了 
        }
    }
}