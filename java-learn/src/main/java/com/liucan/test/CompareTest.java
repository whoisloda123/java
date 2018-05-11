package com.liucan.test;

import org.springframework.stereotype.Component;

@Component
public class CompareTest extends BaseTest {
    @Override
    public void testAll() {
        super.testAll();
        /* *
         * Comparator和Comparable比较
         * Comparable:
         * 排序接口,实现了Comparable接口，意味该类本身支持排序，直接通过Arrays.sort() 或 Collections.sort()进行排序
         * Comparator:
         * 比较器接口,包括两个函数：compare() 和 equals(),类实现Comparator接口，就是一个“比较器”。其它的类可以根据该比较器去排序
         * 综上所述：
         * Comparable是内部比较器，而Comparator是外部比较器
         * 类本身实现了Comparable比较器，它本身支持排序；若它本身没实现Comparable，或者是final，也可以通过外部比较器Comparator进行排序
         * http://www.cnblogs.com/skywang12345/p/3324788.html
         * https://blog.csdn.net/u013256816/article/details/50899416
         */
    }
}
