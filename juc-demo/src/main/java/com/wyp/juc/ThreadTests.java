package com.wyp.juc;

import java.util.concurrent.TimeUnit;

/**
 * A、B、C三个线程依次执行完成后执行main线程
 */
public class ThreadTests {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----A----");
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----B----");
        }, "t2");

        Thread t3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----C----");
        }, "t3");
        t1.start();
        t2.start();
        t3.start();

        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----main----");
    }

    static class ThreadA extends Thread {

        private ThreadC threadC;

        public void setThreadC(ThreadC threadC) {
            this.threadC = threadC;
        }

        @Override
        public void run() {

        }

    }

    static class ThreadB extends Thread {

        private ThreadA threadA;

        public void setThreadA(ThreadA threadA) {
            this.threadA = threadA;
        }

        @Override
        public void run() {

        }

    }

    static class ThreadC extends Thread {

        private ThreadB threadB;

        public void setThreadB(ThreadB threadB) {
            this.threadB = threadB;
        }

        @Override
        public void run() {

        }

    }


}







