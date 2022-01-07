package com.wyp.javabasicapi.StopWatch;


import org.springframework.util.StopWatch;

/**
 * 使用 StopWatch 优雅打印执行耗时
 * https://mp.weixin.qq.com/s/4Px7UwMiPfEyg537JTlabw
 */
public class StopWatchTest {


    public static void main(String[] args) throws Exception {
        // test01();
        StopWatch sw = new StopWatch();
        sw.start("A");
        Thread.sleep(500);
        sw.stop();
        sw.start("B");
        Thread.sleep(300);
        sw.stop();
        sw.start("C");
        Thread.sleep(200);
        sw.stop();
        System.out.println(sw.prettyPrint());

        int taskCount = sw.getTaskCount();
        System.out.println("taskCount = " + taskCount);

        String lastTaskName = sw.getLastTaskInfo().getTaskName();
        System.out.println("lastTaskName = " + lastTaskName);

        String shortSummary = sw.shortSummary();
        System.out.println("shortSummary = " + shortSummary);

        double totalTimeSeconds = sw.getTotalTimeSeconds();
        System.out.println("totalTimeSeconds = " + totalTimeSeconds);

    }

    private static void test01() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        //long task simulation
        Thread.sleep(1000);
        sw.stop();
        System.out.println(sw.getTotalTimeMillis());
    }
}
