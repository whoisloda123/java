package com.liucan.mayi;

/**
 * @author liucan
 */
public class Test {

    public static void main(String[] args) {
        int arrys1[] = {1, 2, 3, 4, 5};

        LinkedList linkedList1 = new LinkedList();
        for (int arry : arrys1) {
            linkedList1.initData(arry);
        }

        System.out.println("链表1初始化成功，打印链表1内容");
        linkedList1.print();

        linkedList1.addCycleByValue(2);
        System.out.println("构建有环链表1成功");

        System.out.println("开始判断链表1是否有环");
        LinkedList.Node cycleExistNode = linkedList1.isCycleExist();
        if (null != cycleExistNode) {
            System.out.println("发现链表1有环，节点为：" + cycleExistNode.getValue());
        }

        //////////////////////////////////////////////////////////////////////////////
        System.out.println("---------------------------------------------");
        System.out.println("开始测试2个链表是否相交");
        int arrys2[] = {5, 6, 3, 4, 7};
        LinkedList linkedList2 = new LinkedList();
        LinkedList.Node node = linkedList2.new Node();
        node.setValue(8);

        for (int arry : arrys2) {
            linkedList2.initData(arry);
        }
        linkedList2.addCycleByIndex(node, 2);
        System.out.println("链表2初始化成功，打印链表2内容");
        linkedList2.print();

        int arrys3[] = {1, 2, 3, 4, 5};
        LinkedList linkedList3 = new LinkedList();
        for (int arry : arrys3) {
            linkedList3.initData(arry);
        }
        linkedList3.addCycleByIndex(node, 3);
        System.out.println("链表3初始化成功，打印链表3内容");
        linkedList3.print();

        LinkedList.Node intersectNode = linkedList2.isIntersect(linkedList3);
        if (null != intersectNode) {
            System.out.println("发现2个链表相交，节点为：" + intersectNode.getValue());
        }
    }
}
