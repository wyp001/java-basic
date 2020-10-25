package com.wyp.juc;

import java.util.concurrent.*;

public class MyThreadDemo {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());// 当前运行的系统CPU的个数
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, //核心线程数2
                5, //最大线程数5
                2, // 空闲时间
                TimeUnit.SECONDS,  // 空闲时间单位
                new LinkedBlockingDeque<>(3), //阻塞队列，队列长度3
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略，默认是AbortPolicy策略
                // new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略，CallerRunsPolicy策略,回退给调用者
                // new ThreadPoolExecutor.DiscardOldestPolicy() // DiscardPolicy策略，抛弃队列中等待最久的任务，然后把当前任务加入队列中
                // new ThreadPoolExecutor.DiscardPolicy() // DiscardPolicy策略，默默丢弃无法处理的任务，不予任何处理也不抛出异常，如果允许任务丢失，这是最好的一种策略

        );
        /**
         *  最大可以承受线程 = maximumPoolSize + 阻塞队列的长度，即最多承受 5 + 3 = 8 个线程，
         *  使用AbortPolicy拒绝策略，大于8个线程将报错：
         *  java.util.concurrent.RejectedExecutionException
         */
        try {
            for (int i = 1; i <= 15; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+ "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }


    }

    private static void initThreadPool() {
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个工作线程，类似一个银行5个受理窗口
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个工作线程，类似一个银行1个受理窗口
        // ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个工作线程，线程数量根据情况自动扩容
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个工作线程，线程数量根据情况自动扩容
        //模拟有50个顾客过来银行办理业务。
        try {
            for (int i = 1; i <= 50; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+ "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
