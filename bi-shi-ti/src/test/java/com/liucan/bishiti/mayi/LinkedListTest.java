package com.liucan.bishiti.mayi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liucan
 * @version 19-8-21
 */
public class LinkedListTest extends BaseJunit4Test {

    @Before
    public void before() {
        System.out.println("测试LinkedList开始");
    }

    @After
    public void after() {
        System.out.println("测试LinkedList结束");
    }

    /**
     * 测试链表是否有环
     */
    @Test
    public void isCycleExist() {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(1, 2, 3, 4, 5);
        linkedList.print();

        linkedList.addCycleByValue(2);
        System.out.println("构建有环链表成功");

        System.out.println("开始判断链表是否有环");
        LinkedList.Node cycleExistNode = linkedList.isCycleExist();
        if (null != cycleExistNode) {
            System.out.println("发现链表有环，节点为：" + cycleExistNode.getValue());
        } else {
            System.out.println("未发现链表有环");
        }
    }

    /**
     * 测试2个链表是否相交
     */
    @Test
    public void isIntersect() {
        LinkedList linkedListA = new LinkedList();
        LinkedList.Node node = linkedListA.new Node();
        node.setValue(8);

        //A链表
        linkedListA.addAll(5, 6, 3, 4, 7);
        linkedListA.addByIndex(node, 2);
        linkedListA.print();

        //B链表
        LinkedList linkedListB = new LinkedList();
        linkedListB.addAll(1, 2, 3, 4, 5);
        linkedListB.addByIndex(node, 3);
        linkedListB.print();

        LinkedList.Node intersectNode = linkedListA.isIntersect(linkedListB);
        if (null != intersectNode) {
            System.out.println("发现2个链表相交，节点为：" + intersectNode.getValue());
        } else {
            System.out.println("未发现2个链表相交");
        }
    }
}