package com.wyp.juc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture用法详解
 * https://zhuanlan.zhihu.com/p/344431341
 */
public class CompletableFutureTests {


    public static void main(String[] args) {
        test01();
        // test02();
        // test03();
        // test04();
        // test05();
        // test06();
        // test07();

    }

    /**
     * 获取所有异步任务完成结果——allOf
     */
    public static void  test01() {
        // 异步任务1
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                //使用sleep()模拟耗时操作
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        // 异步任务2
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 2;
        });
        // CompletableFuture.allOf(future1, future1);
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        futures.add(future1);
        futures.add(future2);
        CompletableFuture[] completableFutures = futures.toArray(new CompletableFuture[futures.size()]);
        CompletableFuture.allOf(completableFutures);

        // 输出3
        System.out.println(future1.join()+future2.join());
    }


    /**
     * 获取率先完成的任务结果——anyOf
     */
    public static void test02(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                // 睡5s模拟延时
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new NullPointerException();
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                // 睡眠3s模拟延时
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        CompletableFuture<Object> anyOf = CompletableFuture
                .anyOf(future, future2)
                .exceptionally(error -> {
                    error.printStackTrace();
                    return 2;
                });
        System.out.println(anyOf.join());
    }

    /**
     * 计算完成后续操作1——complete
     */
    public static void test03(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10086;
        });
        future.whenComplete((result, error) -> {
            System.out.println("拨打"+result);
            error.printStackTrace();
        });
    }


    /**
     * 计算完成后续操作2——handle
     */
    public static void test04(){
        // 开启一个异步方法
        CompletableFuture<List> future = CompletableFuture.supplyAsync(() -> {
            List<String> list = new ArrayList<>();
            list.add("语文");
            list.add("数学");
            // 获取得到今天的所有课程
            return list;
        });
        // 使用handle()方法接收list数据和error异常
        CompletableFuture<Integer> future2 = future.handle((list,error)-> {
            // 如果报错，就打印出异常
            error.printStackTrace();
            // 如果不报错，返回一个包含Integer的全新的CompletableFuture
            return list.size();
            // 注意这里的两个CompletableFuture包含的返回类型不同
        });

    }

    public static void test05(){
        // 实例化一个CompletableFuture,返回值是Integer
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 返回null
            return null;
        });
        CompletableFuture<String> exceptionally = future.thenApply(result -> {
            // 制造一个空指针异常NPE
            int i = result;
            return i;
        }).thenApply(result -> {
            // 这里不会执行，因为上面出现了异常
            String words = "现在是" + result + "点钟";
            return words;
        }).exceptionally(error -> {
            // 我们选择在这里打印出异常
            error.printStackTrace();
            // 并且当异常发生的时候，我们返回一个默认的文字
            return "出错啊~";
        });
        exceptionally.thenAccept(System.out::println);
    }

    /**
     * thenCompose()
     */
    public static void test06() {
        CompletableFuture<List<String>> total = CompletableFuture.supplyAsync(() -> {
            // 第一个任务获取美术课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("画笔");
            stuff.add("颜料");
            return stuff;
        }).thenCompose(list -> {
            // 向第二个任务传递参数list(上一个任务美术课所需的东西list)
            CompletableFuture<List<String>> insideFuture = CompletableFuture.supplyAsync(() -> {
                List<String> stuff = new ArrayList<>();
                // 第二个任务获取劳技课所需的工具
                stuff.add("剪刀");
                stuff.add("折纸");
                // 合并两个list，获取课程所需所有工具
                List<String> allStuff = Stream.of(list, stuff).flatMap(Collection::stream).collect(Collectors.toList());
                return allStuff;
            });
            return insideFuture;
        });
        System.out.println(total.join().size());
    }

    /**
     * thenCombine(）
     */
    public static void test07() {
        CompletableFuture<List<String>> painting = CompletableFuture.supplyAsync(() -> {
            // 第一个任务获取美术课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("画笔");
            stuff.add("颜料");
            return stuff;
        });
        CompletableFuture<List<String>> handWork = CompletableFuture.supplyAsync(() -> {
            // 第二个任务获取劳技课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("剪刀");
            stuff.add("折纸");
            return stuff;
        });
        CompletableFuture<List<String>> total = painting
                // 传入handWork列表，然后得到两个CompletableFuture的参数Stuff1和2
                .thenCombine(handWork, (stuff1, stuff2) -> {
                    // 合并成新的list
                    List<String> totalStuff = Stream.of(stuff1, stuff1)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
                    return totalStuff;
                });
        System.out.println(total.join());
    }



}
