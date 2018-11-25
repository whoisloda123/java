package com.liucan.template;

/**
 * @author liucan
 * @version 18-11-24
 */
public class TemplateFunction {

    //泛型方法
    public static <T> void printArray(T[] inputArray) {
        for (T t : inputArray) {
            System.out.println(t);
        }
    }

    //泛型方法-添加限制
    public static <T extends Comparable<T>> T maximum(T x, T y) {
        return x.compareTo(y) > 0 ? x : y;
    }

    public static void upDown(TemplateClass<? extends Number, String> templateClass) {
        templateClass.getMap();
    }
}
