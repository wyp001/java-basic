package com.wyp.jvmtest.case01;

/**
 * 运行此代码，cpu会飙高
 */
public class Math {

    // 一个方法对应一块栈帧内存区域
    public int compute() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        System.out.println(c);
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        while (true) {
            math.compute();
        }
    }
}
