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

    @Autowired
    public MyController(
            OtherTest otherTest,
            RedisTest redisTest,
            JsonTest jsonTest,
            OptionalTest optionalTest,
            FunctionalInterfaceTest functionalInterfaceTest,
            StreamTest streamTest) {
        this.otherTest = otherTest;
        this.redisTest = redisTest;
        this.jsonTest = jsonTest;
        this.optionalTest = optionalTest;
        this.functionalInterfaceTest = functionalInterfaceTest;
        this.streamTest = streamTest;
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
    }
}
