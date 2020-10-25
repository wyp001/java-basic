package com.wyp.juc;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {

        BlockingQueue bq = new ArrayBlockingQueue(3);
        System.out.println(bq.add("a"));
        System.out.println(bq.add("b"));
        System.out.println(bq.add("c"));
        // System.out.println(bq.add("x")); //队列中放入第四个元素时就会报错：java.lang.IllegalStateException: Queue full

        System.out.println(bq.remove());
        System.out.println(bq.remove());
        System.out.println(bq.remove());
        System.out.println(bq.remove()); //队列中存放3个元素，删除第四个时报错：java.util.NoSuchElementException
    }

}
