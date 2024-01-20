package org.algorithm;

public class DeListNode {

    public int key;
    public int val;
    public DeListNode next;
    public DeListNode pre;

    public DeListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public DeListNode(int key) {
        this.key = key;
    }
}
