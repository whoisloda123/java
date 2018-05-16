package com.liucan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/javalearn")
public class MyController {
    @RequestMapping(value = "/test")
    public void test() {
    }
}
