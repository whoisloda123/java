package com.liucan.mayi;

/**
 * 这是一个链表，提供是否有环以及跟另一个链表是否相交的判断功能
 * 1. 判断有环实现函数 isCycleExist函数
 * 2. 判断两个链表是否相交，前置条件是两个链表均无环，故不用考虑有环的情况，实现函数isIntersect
 */
public class LinkedList {
    /**
     * 链表节点
     */
    class Node {
        /**
         * Node值，为一个整数
         */
        int value;
        /**
         * 指向下一个节点，最后一个节点指向null
         */
        Node next;

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * 链表头
     */
    private Node head;
    /**
     * 对下一个节点的引用
     */
    private Node current;

    /**
     * 判斷链表是否有环
     * 此处通过快慢指针的方式实现，快指针每次走2步，慢指针每次走一步
     * 若有环则快指针会在环里面循环，等到慢指针也进去了，2个指针会相遇
     * 也可采用{@link java.util.HashSet}方式实现
     *
     * @return 如果有环则返回环的首结点指针，否则返回null值
     */
    public Node isCycleExist() {
        if (null == head) {
            return null;
        }
        Node headSlow = head; //慢指针
        Node headFast = head; //快指针
        //如何有环则head1和head2会相遇，否则head2会到尽头
        do {
            if (headSlow.getNext() != null && headFast.getNext() != null && headFast.getNext().getNext() != null) {
                headSlow = headSlow.getNext();
                headFast = headFast.getNext().getNext();
            } else {
                return null;
            }
        } while (headSlow != headFast);

        //找到环的起始节点
        headFast = head;
        while (headSlow != headFast) {
            headSlow = headSlow.getNext();
            headFast = headFast.getNext();
        }
        return headSlow;
    }

    /**
     * 判断两个链表是否相交
     * 前提为两个链表均无环
     * 此处先将2个链表连接然后判断是否有环的方式来判断是否相交,如果有环则相交，否则不相交
     *
     * @param linkedList 需要比较的链表
     * @return 返回相交的节点 不为空相交; 为空不相交.
     */
    public Node isIntersect(LinkedList linkedList) {
        if (null == head || null == linkedList || null == linkedList.getHead()) {
            return null;
        }
        Node tailFirst = head;
        while (tailFirst.getNext() != null) {
            tailFirst = tailFirst.getNext();
        }
        Node tailFirstTmp = tailFirst;
        tailFirst.setNext(linkedList.getHead());

        Node cycleExistNode = isCycleExist();
        //保存head的尾节点用于操作后不影响原来的节点
        tailFirstTmp = null;
        return cycleExistNode;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    public void setTail(Node current) {
        this.current = current;
    }

    public Node getTail() {
        return current;
    }

    public void initData(int value) {
        if (null == head) {
            head = new Node();
            head.setNext(null);
            head.setValue(value);
            current = head;
        } else {
            Node node = new Node();
            node.setNext(null);
            node.setValue(value);
            current.setNext(node);
            current = current.next;
        }
    }

    public void addCycleByValue(int value) {
        Node tmp = head;
        while (tmp != null) {
            if (tmp.getValue() == value) {
                current.setNext(tmp);
                return;
            }
            tmp = tmp.getNext();
        }
    }

    public void addCycleByIndex(Node node, int index) {
        Node tmp = head;
        int i = 0;
        while (tmp != null) {
            if (i == index) {
                node.setNext(tmp.getNext());
                tmp.setNext(node);
                return;
            }
            tmp = tmp.getNext();
            i++;
        }
    }

    public void print() {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.getValue() + " ");
            tmp = tmp.getNext();
        }
        System.out.println("");
    }
}