package com.wyp.juc;

import java.util.concurrent.*;

/**
 * @Description:
 * @author: wyupeng
 * @date: 2021/5/21 10:12
 */
public class CallableDemo2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String args[]){
        // 使用
        ExecutorService executor = Executors.newCachedThreadPool();
        CallableDemo2 task = new CallableDemo2();
        Future<Integer> result = executor.submit(task);
        // 注意调用get方法会阻塞当前线程，直到得到结果。
        // 所以实际编码中建议使用可以设置超时时间的重载get方法。
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
