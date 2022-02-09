package com.wyp.threadLocalDemo;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 多线程情况下使用 SimpleDateFormat 进行日期格式化会存在线程安全问题
 * 参考：项目中 SimpleDateFormat 的正确使用 https://www.cnblogs.com/christopherchan/p/11148859.html
 */
public class ThreadLocalTest {

    /** 日期格式化类. */
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String formatDate(Date date) throws ParseException {
        // return sdf.format(date);
        return threadLocal.get().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        // return sdf.parse(strDate);
        return threadLocal.get().parse(strDate);
    }


    public static void main(String[] args) throws InterruptedException {
        /** 单线程下测试. */
        // System.out.println(sdf.format(new Date()));

        /** 多线程下测试. */
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 20; i++) {
            service.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        System.out.println(parse("2018-01-02 09:45:59"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        // 等待上述的线程执行完后
        //shutdown()关闭任务，awaitTermination关闭执行器（executor）。
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
    }

}
