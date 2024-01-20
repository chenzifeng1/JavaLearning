package org.algorithm;

import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description
 * @date 2024/1/15 1:44 PM
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode getList(List<Integer> list) {
        ListNode head = new ListNode(list.get(0));
        ListNode temp = head;
        for (int i = 1; i < list.size(); i++) {
            temp.next = new ListNode(list.get(i));
            temp = temp.next;
        }
        return head;
    }


    public static void sout(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
}
