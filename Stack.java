package com.company;

public class Stack {
    private int[] items = new int[5];
    private int count;

    public void push(int item) {
        if(count == items.length) {
            throw new StackOverflowError();
        }

        items[count++] = item;
    }

    public int pop() {
        if(isEmpty()) {
            throw new IllegalStateException();
        }

        return items[--count];
    }

    public int peek() {
        if(isEmpty()) {
            throw new IllegalStateException();
        }

        return items[count - 1];
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
