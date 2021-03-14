package com.company;

import java.util.NoSuchElementException;

public class LinkedList {
    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int length;

    public void addLast(int item) {
        Node node = new Node(item);

        if(isEmpty()) first = last = node;
        else {
            last.next = node;
            last = node;
        }

        length++;
    }

    public void addFirst(int item) {
        Node node = new Node(item);

        if(isEmpty()) first = last = node;
        else {
            node.next = first;
            first = node;
        }

        length++;
    }

    public int indexOf(int item) {
        int index = 0;
        Node current = first;

        while(current != null) {
            if(current.value == item) {
                return index;
            }

            index++;
            current = current.next;
        }

        return -1;
    }

    public boolean contains(int item) {
        return indexOf(item) != -1;
    }

    public void removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        if(first == last) {
            first = last = null;
        }else {
            Node second = first.next;
            first.next = null;
            first = second;
        }

        length--;
    }

    public void removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        if(first == last) {
            first = last = null;
        }else {
            Node previous = getPrevious(last);
            last = previous;
            last.next = null;
        }

        length--;
    }

    public int size() {
        return this.length;
    }

    public int[] toArray() {
        int[] array = new int[this.length];
        Node current = first;

        int index = 0;
        while(current != null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }
    
    public void reverse() {
        if(isEmpty()) return;

        Node previous = first;
        Node current = first.next;
        while(current != null) {
            Node next = current.next;
            current.next = previous;

            previous = current;
            current = next;
        }

        last = first;
        last.next = null;
        first = previous;
    }

    public int getKthFromTheEnd(int k) {
        if(isEmpty()) {
            throw new IllegalStateException();
        }

        Node a = first;
        Node b = first;

        for(int i = 0; i < k - 1; i++) {
            b = b.next;

            if(b == null) {
                throw new IllegalArgumentException();
            }
        }

        while(b != last) {
            a = a.next;
            b = b.next;
        }

        return a.value;
    }

    public void printMiddleElement() {
        if(isEmpty()) {
            throw new IllegalStateException();
        }

        Node current = first;
        Node middle = first;

        while(current != last && current.next != last) {
            current = current.next.next;
            middle = middle.next;
        }

        if(current == last) {
            System.out.println(middle.value);
        }else {
            System.out.println(middle.value + " " + middle.next.value);
        }
    }

    private boolean isEmpty() {
        return first == null;
    }

    private Node getPrevious(Node node) {
        Node current = first;
        while(current != null) {
            if(current.next == node) return current;
            current = current.next;
        }

        return null;
    }

}
