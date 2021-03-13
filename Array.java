package com.company;
import java.util.HashSet;

public class Array {
    private int[] items;
    private int length;

    public Array(int length) {
        this.items = new int[length];
    }

    public void insert(int item) {
        if(items.length == length) {
            int[] newItems = new int[length * 2];

            for(int i = 0; i < length; i++) {
                newItems[i] = items[i];
            }

            items = newItems;
        }

        items[length++] = item;
    }

    public void removeAt(int index) {
        if(index < 0 || index >= length) {
            throw new IllegalArgumentException();
        }

        for(int i = index; i < length - 1; i++) {
            items[i] = items[i + 1];
        }

        length--;
    }

    public int indexOf(int item) {
        for(int i = 0; i < length; i++) {
            if(items[i] == item) {
                return i;
            }
        }

        return -1;
    }

    public int max() {
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < length; i++ ){
            if(items[i] > max) max = items[i];
        }

        return max;
    }

    public static Object[] intersect(int[] firstArray, int[] secondArray) {
        HashSet<Integer> common = new HashSet<>();

        for(int first : firstArray) {
            for(int second : secondArray) {
                if(first == second && !common.contains(first)) {
                    common.add(first);
                }
            }
        }

        return common.toArray();
    }

    public void reverse() {
        for(int i = 0; i < length / 2; i++) {
            int tmp = items[i];
            items[i] = items[length - 1 - i];
            items[length - 1 - i] = tmp;
        }
    }

    public void insertAt(int item, int index) {
        if(index < 0 || index >= length) {
            throw new IllegalArgumentException();
        }

        items[index] = item;
    }
}
