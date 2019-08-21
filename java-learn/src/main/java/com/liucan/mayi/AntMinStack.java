package com.liucan.mayi;

import java.util.Arrays;
import java.util.Stack;

/**
 * AntMinStack
 *
 * @author commons
 */
public class AntMinStack {

    private final Stack<Integer> dataStack = new Stack<>();

    private final Stack<Integer> minStack = new Stack<>();

    private volatile Integer minValue;

    public void push(int data) {
        dataStack.push(data);

        if (minStack.empty()) {
            minStack.push(data);
        } else {
            Integer peek = minStack.peek();
            if (peek > data) {
                minStack.push(data);
            }
        }

    }

    public int pop() {
        if (dataStack.empty() || minStack.empty()) {
            throw new IllegalStateException("Stack is empty");
        }

        int popData = dataStack.pop();
        int peekMin = minStack.peek();

        if (popData == peekMin) {
            minValue = peekMin;
        } else if (popData < peekMin) {
            minStack.pop();
            minStack.push(popData);
            minValue = popData;
        }
        print();
        return popData;
    }

    public int min() {
        if (dataStack.empty() || minStack.empty()) {
            throw new IllegalStateException("Stack is empty");
        }

        Integer peek = minStack.peek();
        if (minValue != null) {
            if (peek.compareTo(minValue) > 0) {
                return minValue;
            }
            return peek;
        }
        return minStack.peek();
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
        return "AntMinStack-> dataStack:" + dataStack.toString() + ", minStack:" + minStack.toString();
    }

    public void print() {
        System.out.println(toString());
    }

    public static void main(String[] args) {
        AntMinStack antMinStack = new AntMinStack();

        antMinStack.pushAll(2, 1, 3, 2, 1);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        //antMinStack.pop();
        System.out.println("min:" + antMinStack.min());
        antMinStack.clear();

        antMinStack.pushAll(1, 2, 3, 2, 1);
        antMinStack.pop();
        antMinStack.pop();
        antMinStack.pop();
        System.out.println("min:" + antMinStack.min());
        antMinStack.clear();

        antMinStack.pushAll(1, 2, 3, 4, 5);
        System.out.println("min:" + antMinStack.min());
        antMinStack.clear();

        antMinStack.pushAll(5, 4, 3, 2, 1);
        System.out.println("min:" + antMinStack.min());
        antMinStack.clear();

        antMinStack.pushAll(5, 4, 1, 2, 3);
        System.out.println("min:" + antMinStack.min());
        antMinStack.clear();
    }
}
