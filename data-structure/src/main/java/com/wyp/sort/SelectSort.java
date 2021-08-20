package com.wyp.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 算法思路讲解（视频）：
 * 057_尚硅谷_选择排序算法思路图解
 * https://www.bilibili.com/video/BV1E4411H73v?p=57
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 90, 123};

        selectSort(arr);


    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) { //进行几轮比较
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {  //比较出该轮中数组的最小值
                if (arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i]; //把第 i 索引位置的值放到前轮最小值的索引位置上
                arr[i] = min; // 把最小值放到第 i 索引位置上
            }
            System.out.println("第"+ (i +1) + "轮后");
            System.out.println(Arrays.toString(arr));
        }
    }


}
