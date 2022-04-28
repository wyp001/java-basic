package com.wyp.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t 没有返回，update mysql ok");
        });

        // 异步回调
        CompletableFuture cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+ "\t cf2");
            int age = 10/0;
            return 1024;
        }).whenComplete((t,e)->{
            System.out.println("*****t: "+ t);
            System.out.println("*****e: "+ e);
        }).exceptionally(f ->{
            System.out.println("****exception: "+ f.getMessage());
            return 4444;
        });
        /**
         * 执行过程中正常执行，没有异常时返回结果1024
         * 执行过程中遇到异常，返回4结果444,
         * 可以通过返回的结果来判断执行过程中是否出现异常
         */
        System.out.println("----最终的返回结果：----"+ cf2.get());




    }

}
