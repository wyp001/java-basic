package com.wyp.juc;

/**
 * 线程组同一异常处理
 */
public class ThreadGroupDemo {
    public static void main(String[] args) {
        ThreadGroup threadGroup1 = new ThreadGroup("group1") {
            // 继承ThreadGroup并重新定义以下方法
            // 在线程成员抛出unchecked exception
            // 会执行此方法
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("-----异常信息--------"+ t.getName() + ": " + e.getMessage());
            }
        };

        // 这个线程是threadGroup1的一员
        Thread thread1 = new Thread(threadGroup1, () -> {
            System.out.println("----------ThreadGroupDemo--------main()----------thread1---------\n");
            // 抛出unchecked异常
            throw new RuntimeException("测试异常");
        });

        thread1.start();
    }
}