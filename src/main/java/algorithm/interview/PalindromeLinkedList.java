package org.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * <p>
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 示例 1：
 * <p>
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：head = [1,2]
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 * <p>
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * @description
 * @date 2024/1/15 10:04 AM
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 2};

        ListNode head = new ListNode(1);

        ListNode temp = head;

        for (int i = 1; i < array.length; i++) {
            ListNode next = new ListNode(array[i]);
            temp.next = next;
            temp = temp.next;
        }

        boolean process = process(head);
        System.out.println(process);

    }


    public static boolean process(ListNode head) {
        List<Integer> list = new ArrayList<>();

        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        temp = head;
        int index = list.size() - 1;

        while (temp != null) {
            if (temp.val != list.get(index--)) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    public static boolean process1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }



        return false;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
