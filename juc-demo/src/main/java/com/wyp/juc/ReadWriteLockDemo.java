package com.wyp.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{

    private volatile Map<String, Object> map = new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t -----写入数据"+ key);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t -----写入"+ key + "完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 读取数据"+ key);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取结果为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * 多线程同时读一个资源类,为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果一个线程想去写共享资源类，就不应该再有其他线程可以对该资源进行读或写
 * 也即：
 *      读—读 能共存
 *      读—写 不能共存
 *      写—写 不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            int tmpInt = i;
            new Thread(()->{
                myCache.put(tmpInt+ "", tmpInt+ "");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int tmpInt = i;
            new Thread(()->{
                myCache.get(tmpInt+ "");
            },String.valueOf(i)).start();
        }

    }
}
