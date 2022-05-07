package com.wyp.javabasicapi.StopWatch;


import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * stopWatch无法多线程公用，会出现线程一直阻塞，控制台报错信息：
 * Can't start StopWatch: it's already running
 */
public class StopWatchTest02 {

    public static void main(String[] args) {
        ExecutorService taskExecutor = Executors.newFixedThreadPool(5);
        StopWatch stopWatch = new StopWatch();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            stopWatch.start("---aaa---");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopWatch.stop();
            System.out.println("---1111------");
            return 1;
        }, taskExecutor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            stopWatch.start("---bbb---");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopWatch.stop();
            System.out.println("---2222------");
            return 2;
        }, taskExecutor);

        List<CompletableFuture<?>> futureList = new ArrayList<>();
        futureList.add(future1);
        futureList.add(future2);
        CompletableFuture[] completableFutures = futureList.toArray(new CompletableFuture[futureList.size()]);
        CompletableFuture.allOf(completableFutures);

        for (CompletableFuture future : completableFutures) {
            try {
                Object res = future.get();
                System.out.println("---结果---" + res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(stopWatch.prettyPrint());
    }


}
