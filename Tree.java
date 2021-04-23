package com.company;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }
    }

    private Node root;

    public void insert(int value) {
        Node node = new Node(value);

        if(root == null) {
            root = node;
            return;
        }

        Node current = root;
        while(true) {
           if(value < current.value) {
               if(current.leftChild == null) {
                   current.leftChild = node;
                   break;
               }
               current = current.leftChild;
           }else {
               if(current.rightChild == null) {
                   current.rightChild = node;
                   break;
               }
               current = current.rightChild;
           }
        }
    }

    public boolean find(int value) {
        Node current = root;
        while(current != null) {
            if(value < current.value) {
                current = current.leftChild;
            }else if (value > current.value){
                current = current.rightChild;
            }else {
                return true;
            }
        }

        return false;
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) {
        if(root != null) {
            System.out.println(root.value);
            traversePreOrder(root.leftChild);
            traversePreOrder(root.rightChild);
        }
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root) {
        if(root != null) {
            traverseInOrder(root.leftChild);
            System.out.println(root.value);
            traverseInOrder(root.rightChild);
        }
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {
        if(root != null) {
            traversePostOrder(root.leftChild);
            traversePostOrder(root.rightChild);
            System.out.println(root.value);
        }
    }

    public int height() {
        return height(root);
    }

    // Used Post Order Traversal
    private int height(Node root) {
        if(root == null) return -1;
        if(isLeaf(root)) return 0;

        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    public int min() {
        return min(root);
    }

    // O(n), Because we traverse to every node
    // Used Post Order Traversal
    private int min(Node root) {
        if(root == null) return Integer.MAX_VALUE;
        if(isLeaf(root)) return root.value;

        int left = min(root.leftChild);
        int right = min(root.rightChild);

        return Math.min(root.value, Math.min(left, right));
    }

    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

    public boolean equals(Tree other) {
        if(other == null) return false;
        return equals(root, other.root);
    }

    // Used Pre Order Traversal
    private boolean equals(Node first, Node second) {
        if(first == null && second == null) return true;

        if(first != null && second != null) {
            return first.value == second.value
                    && equals(first.leftChild, second.leftChild)
                    && equals(first.rightChild, second.rightChild);
        }

        return false;
    }

    // Pre Order Traversal with Ranges
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int min, int max) {
        if(root == null) return true;
        if(root.value < min || root.value > max) return false;

        return isBinarySearchTree(root.leftChild, min, root.value - 1)
                && isBinarySearchTree(root.rightChild, root.value + 1, max);
    }


    public List<Integer> getNodesAtDistance(int distance) {
        List<Integer> list = new ArrayList<>();
        getNodesAtDistance(root, distance, list);

        return list;
    }

    private void getNodesAtDistance(Node root, int distance, List<Integer> list) {
        if(root == null) return;

        if(distance == 0) {
            list.add(root.value);
            return;
        }

        getNodesAtDistance(root.leftChild, distance - 1, list);
        getNodesAtDistance(root.rightChild, distance - 1, list);
    }

    // Depth First Traversal
    public void traverseLevelOrder() {
        for(int i = 0; i <= height(); i++) {
            for(var value : getNodesAtDistance(i)) {
                System.out.println(value);
            }
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if(root == null) return 0;
        return 1 + size(root.leftChild) + size(root.rightChild);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node root) {
        if(root == null) return 0;
        if(isLeaf(root)) return 1;

        return countLeaves(root.leftChild) + countLeaves(root.rightChild);
    }

    public int max() {
        return max(root);
    }

    private int max(Node root) {
        if(root == null) return Integer.MIN_VALUE;
        if(isLeaf(root)) return root.value;

        int left = max(root.leftChild);
        int right = max(root.rightChild);

        return Math.max(root.value, Math.max(left, right));
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node root, int value) {
        if(root == null) return false;
        if(root.value == value) return true;

        return contains(root.leftChild, value) || contains(root.rightChild, value);
    }
}
