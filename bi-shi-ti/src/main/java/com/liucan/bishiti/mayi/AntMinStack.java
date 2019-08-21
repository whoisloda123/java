package com.liucan.bishiti.mayi;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author liucan
 *  通过最小栈保存数据栈里面栈底元素，以及比栈底元素小或相等的元素
 *  如数据栈内容为[4,3,2,1,5,2,1] 则最小栈内容为[4,3,2,1,1]
 *  push的数据比最小栈栈顶元素小则push
 *  pop的数据和最小栈顶元素一样，则同时也pop出最小栈
 */
public class AntMinStack {
    /*
     * 数据栈
     */
    private final Stack<Integer> dataStack = new Stack<>();
    /*
     * 最小栈
     */
    private final Stack<Integer> minStack = new Stack<>();

    /**
     * push 放入元素
     *
     * @param data 元素
     */
    public void push(int data) {
        dataStack.push(data);

        if (minStack.empty()) {
            minStack.push(data);
        } else {
            Integer min = minStack.peek();
            if (min != null && min >= data) {
                minStack.push(data);
            }
        }
        System.out.println("push数据" + data + "后 -> " + toString());
    }

    /**
     * pop 推出元素,如果pop出来的数据和最小栈元素一样，则同时也pop出最小栈
     * @return 栈顶元素
     * @throws EmptyStackException  if this stack is empty.
     */
    public int pop() {
        if (dataStack.empty() || minStack.empty()) {
            throw new EmptyStackException();
        }

        //System.out.println("pop前 -> " + toString());
        Integer data = dataStack.pop();
        if (data != null && data.intValue() == minStack.peek()) {
            minStack.pop();
        }

        System.out.println("pop数据" + data + "后 -> " + toString());
        return data;
    }

    /**
     * min 最小函数，调用该函数，可直接返回当前AntMinStack的栈的最小值
     *
     * @return 栈最小值
     * @throws EmptyStackException  if this stack is empty.
     */
    public int min() {
        return minStack.peek();
    }

    @Override
    public String toString() {
        try {
            if (dataStack.empty()) {
                return "数据栈为空，没有最小值";
            } else {
                return "数据栈信息:" + dataStack.toString() +
                        ", 最小栈信息:" + minStack.toString() +
                        ", 最小值:" + (!minStack.empty() ? min() : "");
            }
        } catch (Exception e) {
            System.out.println("toString exception:" + e);
            return "";
        }
    }

    public void clear() {
        dataStack.clear();
        minStack.clear();
        System.out.println("清除数据成功");
    }

    public void pushAll(int... values) {
        Arrays.stream(values).forEach(this::push);
    }
}
