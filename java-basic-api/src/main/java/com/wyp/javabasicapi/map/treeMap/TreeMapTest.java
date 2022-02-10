package com.wyp.javabasicapi.map.treeMap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Java TreeMap使用方法 https://www.cnblogs.com/jack-L/p/10443340.html
 */
public class TreeMapTest {

    public static void main(String[] args) {

        //1、使用默认的TreeMap 构造函数，其中key值需要有比较规则
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(new Integer(2), "BB");
        map.put(new Integer(1), "AA");
        map.put(new Integer(5), "EE");
        map.put(new Integer(3), "CC");
        map.put(new Integer(4), "DD");
        map.put(new Integer(2), "AA");   //验证重复key是否能够插入

        //使用遍历EntrySet方式
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key:" + entry.getKey() + " --- value:" + entry.getValue());
        }

        //2、使用默认的TreeMap 构造函数，Key中添加自定义类型,自定义类型必须继承Comparator
        System.out.println("-------------------2、使用默认的TreeMap 构造函数，Key中添加自定义类型,自定义类型必须继承Comparator-----------------------");
        TreeMap<person, String> mapPer = new TreeMap<>();
        mapPer.put(new person("张三", 22), "6K");
        mapPer.put(new person("老王", 35), "29K");
        mapPer.put(new person("小张", 31), "11K");
        for (Map.Entry<person, String> entry : mapPer.entrySet()) {
            System.out.println("Key:" + entry.getKey() + " --- value:" + entry.getValue());
        }

        //3、使用比较器类来来实现排序，自定义类型不用来继承Comparator
        System.out.println("-------------------3、使用比较器类来来实现排序，自定义类型不用来继承Comparator-----------------------");
        TreeMap<Book, String> mapBook = new TreeMap<>(new BookComparator());
        mapBook.put(new Book("流浪地球", 60), "200页");
        mapBook.put(new Book("三体", 100), "400页");
        mapBook.put(new Book("大秦帝国", 180), "900页");
        for (Map.Entry<Book, String> entry : mapBook.entrySet()) {
            System.out.println("Key:" + entry.getKey() + " --- value:" + entry.getValue());
        }

    }
}

//自定义person类
class person implements Comparable<person> {
    String name;
    int age;

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名:" + this.name + " 年龄:" + this.age;
    }

    @Override
    public int compareTo(person o) {
        if (o.age > this.age) {
            return 1;
        } else if (o.age < this.age) {
            return -1;
        }
        return 0;
    }
}

//自定义book类
class Book {
    String name;
    double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "书名:" + this.name + " 价格:" + this.price;
    }
}

//book比较器类
class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.price > o2.price) {
            return 1;
        } else if (o1.price < o2.price) {
            return -1;
        }
        return 0;
    }
}