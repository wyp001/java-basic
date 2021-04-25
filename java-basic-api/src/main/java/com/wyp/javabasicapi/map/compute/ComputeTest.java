package com.wyp.javabasicapi.map.compute;

import java.util.HashMap;

/**
 * @author wangyupeng
 * @date 2021年04月25日 10:19
 */
public class ComputeTest {

    public static void main(String[] args) {
        HashMap<String, Integer> prices = new HashMap<>();
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 重新计算鞋子打了10%折扣后的值
        int newPrice = prices.compute("Shoes", (key, value) -> value - value * 10/100);
        System.out.println("Discounted Price of Shoes: " + newPrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);
    }
}
