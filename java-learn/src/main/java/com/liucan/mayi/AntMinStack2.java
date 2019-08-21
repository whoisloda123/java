package com.liucan.mayi;

import java.util.Arrays;
import java.util.Stack;

/**
 * AntMinStack
 *
 * @author commons
 */
public class AntMinStack2 {

    private final Stack<Integer> dataStack = new Stack<>();

    private final Stack<Integer> minStack = new Stack<>();

    private volatile Integer minValue;

    public synchronized void push(int data) {
        dataStack.push(data);

        if (minStack.empty()) {
            minStack.push(data);
        } else {
            Integer peek = minStack.peek();
            if (data < peek) {
                minStack.push(data);
                minValue = data;
            }
        }

    }

    public synchronized int pop() {
        if (dataStack.empty() || minStack.empty()) {
            throw new IllegalStateException("Stack is empty");
        }

        System.out.println("pop前 -> " + toString());

        int popData = dataStack.pop();
        int peekMin = minStack.peek();

        if (popData == peekMin) {
            if (dataStack.size() == minStack.size() - 1) {
                minStack.pop();
            }
            minValue = peekMin;
        } else if (popData < peekMin) {
            minStack.pop();
            minStack.push(popData);
            minValue = popData;
        }

        System.out.println("pop后 -> " + toString());

        return popData;
    }

    public int min() {
        if (dataStack.empty() || minStack.empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return minValue != null ? minValue : minStack.peek();
    }

    public void pushAll(int... data) {
        Arrays.stream(data).forEach(this::push);
    }

    public void clear() {
        dataStack.clear();
        minStack.clear();
        minValue = null;
    }

    @Override
    public String toString() {
        return "AntMinStack-> dataStack:" + dataStack.toString() +
                ", minStack:" + minStack.toString() +
                ", minValue:" + minValue;
    }

    public static void main(String[] args) {
        AntMinStack2 antMinStack2 = new AntMinStack2();

        antMinStack2.pushAll(2, 1, 3, 2, 1);
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        System.out.println("min: " + antMinStack2.min());
        antMinStack2.clear();

        antMinStack2.pushAll(1, 2, 3, 2, 1);
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        System.out.println("min: " + antMinStack2.min());
        antMinStack2.clear();

        antMinStack2.pushAll(1, 2, 3, 4, 5);
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        System.out.println("min: " + antMinStack2.min());
        antMinStack2.clear();

        antMinStack2.pushAll(5, 4, 3, 2, 1);
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        System.out.println("min: " + antMinStack2.min());
        antMinStack2.clear();

        antMinStack2.pushAll(5, 4, 1, 2, 3);
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        antMinStack2.pop();
        System.out.println("min: " + antMinStack2.min());
        antMinStack2.clear();
    }
}

