package com.liucan.mayi;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author liucan
 *  通过最小栈保存数据栈里面栈底元素，以及比栈底元素小或相等的元素
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

        System.out.println("pop前 -> " + toString());
        Integer data = dataStack.pop();
        if (data != null && data.intValue() == minStack.peek()) {
            minStack.pop();
        }

        System.out.println("pop后 -> " + toString());
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
            return "AntMinStack-> dataStack:" + dataStack.toString() +
                    ", minStack:" + minStack.toString() +
                    ", minValue:" + min();
        } catch (Exception e) {
            System.out.println("toString exception:" + e);
            return "";
        }
    }

    public void clear() {
        dataStack.clear();
        minStack.clear();
    }

    public void pushAll(int... values) {
        Arrays.stream(values).forEach(this::push);
    }

    public static void main(String[] args) {
        AntMinStack antMinStack = new AntMinStack();

        antMinStack.pushAll(3, 2, 1, 4, 2, 1);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.clear();

        antMinStack.pushAll(1, 2, 3, 2, 1);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.clear();

        antMinStack.pushAll(5, 4, 1, 3, 2);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.clear();
    }
}
