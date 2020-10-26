package com.wyp.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class User{
    private int id;
    private String username;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
    }

    public User(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}

/**
 * 需求：按照给定数据，找出满足一下的用户，也即一下条件全部满足
 * 偶数id切年龄大于24，用户名称转换成大写，用户名称倒排序，只输出一个用户名称
 */
public class StreamDemo {

    public static void main(String[] args) {

        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 25);
        User u4 = new User(14, "d", 26);
        User u5 = new User(15, "e", 27);
        User u6 = new User(16, "f", 28);
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5,u6);
        list.stream()
                .filter(user -> {
                    return user.getId() % 2 == 0;
                })
                .filter(user -> {
                    return user.getAge() > 24;
                })
                .map(user -> {
                    return user.getUsername().toUpperCase();
                })
                .sorted((user1, user2) -> {
                    return user1.compareTo(user2);
                })
                .limit(1)
                .forEach(System.out::print);





    }



}
