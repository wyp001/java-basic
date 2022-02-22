package com.wyp.jvmtest.controller;


import com.wyp.jvmtest.case01.Math;
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

}
