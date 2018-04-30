package com.liucan.utils;

public class Utils {
    public static void printMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        System.out.println(e.getMethodName() + "--------------------------------------------------");
    }
}
