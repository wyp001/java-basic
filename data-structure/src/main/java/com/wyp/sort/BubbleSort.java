package com.wyp.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        bubbleSort(arr);

    }


    public static void bubbleSort(int[] arr){
        boolean flag = false;
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                if (arr[j] > arr[j+1]){
                    tmp = arr[j];
                    arr[j] = arr[j+ 1];
                    arr[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
            System.out.printf("第 %d 趟比较后的数组为%s", i+1, Arrays.toString(arr));
            System.out.println();
            flag = false;
        }
    }

}
