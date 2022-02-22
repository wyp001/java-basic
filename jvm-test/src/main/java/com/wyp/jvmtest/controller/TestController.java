package com.wyp.jvmtest.controller;


import com.wyp.jvmtest.case01.Math;
import com.wyp.jvmtest.case02.ArthasDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/cpu")
    public void test01(){
        Math math = new Math();
        while (true) {
            math.compute();
        }
    }

    @GetMapping("/arthas/case1")
    public void test02(){
        // 模拟 CPU 过高
        ArthasDemo.cpuHigh();
    }

    @GetMapping("/arthas/case2")
    public void test03(){
        // 模拟线程死锁
        ArthasDemo.deadThread();
    }

    @GetMapping("/arthas/case3")
    public void test04(){
        // 不断的向 hashSet 集合增加数据
        ArthasDemo.addHashSetThread();
    }

}
