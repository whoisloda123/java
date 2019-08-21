package com.liucan.bishiti.mayi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liucan
 * @version 19-8-21
 */
public class AntMinStack2Test extends BaseJunit4Test {

    @Before
    public void before() {
        System.out.println("测试AntMinStack开始");
    }

    @After
    public void after() {
        System.out.println("测试AntMinStack结束");
    }

    @Test
    public void min() {
        AntMinStack antMinStack = new AntMinStack();

        antMinStack.pushAll(3, 2, 1, 4, 2, 1);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.push(6);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.clear();

        System.out.println("---------------------------------");
        antMinStack.pushAll(8, 4, 3, 2);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.push(1);
        antMinStack.pop();
        antMinStack.clear();

        System.out.println("---------------------------------");
        antMinStack.pushAll(5, 4, 1, 3, 2);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.clear();
    }
}