package com.liucan.controller;

import com.liucan.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/javalearn")
public class MyController {
    private final OtherTest otherTest;
    private final RedisTest redisTest;
    private final JsonTest jsonTest;
    private final OptionalTest optionalTest;
    private final FunctionalInterfaceTest functionalInterfaceTest;
    private final StreamTest streamTest;
    private final CompareTest compareTest;
    private final MybatisTest mybatisTest;
    private final MybatisSpringTest mybatisSpringTest;

    @Autowired
    public MyController(
            OtherTest otherTest,
            RedisTest redisTest,
            JsonTest jsonTest,
            OptionalTest optionalTest,
            FunctionalInterfaceTest functionalInterfaceTest,
            StreamTest streamTest,
            CompareTest compareTest,
            MybatisTest mybatisTest,
            MybatisSpringTest mybatisSpringTest) {
        this.otherTest = otherTest;
        this.redisTest = redisTest;
        this.jsonTest = jsonTest;
        this.optionalTest = optionalTest;
        this.functionalInterfaceTest = functionalInterfaceTest;
        this.streamTest = streamTest;
        this.compareTest = compareTest;
        this.mybatisTest = mybatisTest;
        this.mybatisSpringTest = mybatisSpringTest;
    }

    @RequestMapping(value = "/test")
    public void test() {
        //otherTest
        otherTest.testAll();
        //redisTest
        redisTest.testAll();
        //jsonTest
        jsonTest.testAll();
        //optionalTest
        optionalTest.testAll();
        //functionalInterfaceTest
        functionalInterfaceTest.testAll();
        //streamTest
        streamTest.testAll();
        //compareTest
        compareTest.testAll();
        //mybatisTest
        mybatisTest.testAll();
        //mybatisSpringTest
        mybatisSpringTest.testAll();
    }
}
