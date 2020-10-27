package com.wyp.juc.mytest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<String>{

    private static final int ADJUST_VALUE = 20;
    private String str;
    private int begin;
    private int end;
    private String result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public MyTask(String str, int begin, int end) {
        this.str = str;
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected String compute() {
        if(end -begin <= ADJUST_VALUE){
            result = str.substring(begin, end);
        }else {
            int middle = (begin + end + 1)/2;
            MyTask task01 = new MyTask(str, begin,middle);
            MyTask task02 = new MyTask(str,middle,end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}

/**
 * ForkJoin demo 分支合并框架demo
 * 模拟下载大文件, string字符串当做大文件，
 * 每次截取一定长度，多个线程执行，最终拼接起来，
 * 保证最后拼接起来的顺序和原来字符串的顺序一致
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //数据准备
        StringBuffer sb = new StringBuffer();
        String str1 = "";
        String str2 = "";
        String str3 = "";
        for (int i = 1; i < 10; i++) {
            String item1 = "a"+ i;
            str1 = str1 + item1;
            String item2 = "b"+ i;
            str2 = str2 + item2;
            String item3 = "c"+ i;
            str3 = str3 + item3;
        }
        sb.append(str1);
        sb.append(str2);
        sb.append(str3);
        String str = sb.toString();
        System.out.println("-----需要下载的字符串-----\n"+ str);

        // 干活
        MyTask myTask = new MyTask(str, 0, str.length());
        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<String> forkJoinTask = threadPool.submit(myTask);
        System.out.println("-------下载下来的字符串-----\t"+forkJoinTask.get());
        threadPool.shutdown();
    }
}
