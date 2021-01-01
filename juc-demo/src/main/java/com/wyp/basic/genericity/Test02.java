package com.wyp.basic.genericity;

import java.util.ArrayList;
import java.util.List;

public class Test02 {

    private <T> void test(List<? super T> dst, List<T> src){
        for (T t : src) {
            dst.add(t);
        }
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        new Test02().test(animals,dogs);
    }
}
