package com.wyp.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 题目：举例说明集合类是线程不安全的
 * 1 故障出现：
 *  java.util.ConcurrentModificationException
 * 2 导致原因：
 *
 * 3 优化建议：
 *  3.1 使用Vector代替ArrayList
 *
 *
 */
public class NotSafeDemo {

    public static void main(String[] args) {
//        listNotSafe();
//        setNotSafe();
        mapNotSafe();


    }

    private static void mapNotSafe() {
        // Map map = new HashMap();//java.util.ConcurrentModificationException
        // Map map = Collections.synchronizedMap(new HashMap<>()); // 解决方法1
        Map map = new ConcurrentHashMap();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map.entrySet());
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        /**
         * 多线程下Set线程安全问题的解决方法
         * 1、Set<String> set = Collections.synchronizedSet(new HashSet<>())
         * 2、Set<String> set = new CopyOnWriteArraySet();
         */
//        Set<String> set = new HashSet<>(); // 报错：java.util.ConcurrentModificationException
//        Set<String> set = Collections.synchronizedSet(new HashSet<>()); //
        Set<String> set = new CopyOnWriteArraySet(); //
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //        List list = new ArrayList(); // 报错：java.util.ConcurrentModificationException
//        List list = new Vector(); // 使用线程安全的list实现类Vector
//        List list = Collections.synchronizedList(new ArrayList<>()); //使用Collections.synchronizedList将ArrayList转换成线程安全的list
        /**
         * 写时复制CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候,不直接往当前容器object[]添加,而是先将当前容器object[]进行copy,
         * 复制出一个新的容器object[] newElements,然后新的容器object[] newELements里添加元素,添加完元素之后,
         * 再将原容器的引用指向新的容器setArray (newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读,
         * 而不需要加锁,因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想,读和写不同的容器
         */
        List list = new CopyOnWriteArrayList();//使用CopyOnWriteArrayList实现类
        list.forEach(System.out::println);

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
