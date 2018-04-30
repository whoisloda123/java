package com.liucan.controller;

import com.liucan.test.OtherTest;
import com.liucan.test.RedisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/javalearn")
public class MyController {
    private final OtherTest otherTest;
    private final RedisTest redisTest;

    @Autowired
    public MyController(
            OtherTest otherTest,
            RedisTest redisTest) {
        this.otherTest = otherTest;
        this.redisTest = redisTest;
    }

    @RequestMapping(value = "/test")
    public void test() {
        //otherTest
        otherTest.testAll();
        //redisTest
        redisTest.testAll();
    }
}
