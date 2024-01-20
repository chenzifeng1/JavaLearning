package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * @description
 * @date 2024/1/16 10:23 AM
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        // 243 564
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(7);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);


        ListNode list = new AddTwoNumbers().addTwoNumbers(l1, l2);
        while (list != null) {
            System.out.println(list.val);
            list = list.next;
        }

    }

    public ListNode addTwoNumbers(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }
        ListNode h1 = list1;
        ListNode h2 = list2;
        ListNode head;
        int num = h1.val + h2.val;
        boolean isCarry = false;
        if (num >= 10) {
            head = new ListNode(num - 10);
            isCarry = true;
        } else {
            head = new ListNode(num);
        }

        ListNode temp = head;
        h1 = h1.next;
        h2 = h2.next;

        while (h1 != null && h2 != null) {
            int tempNum = h1.val + h2.val + (isCarry ? 1 : 0);
            if (tempNum >= 10) {
                temp.next = new ListNode(tempNum - 10 );
                isCarry = true;
            } else {
                temp.next = new ListNode(tempNum);
                isCarry = false;
            }
            temp = temp.next;
            h1 = h1.next;
            h2 = h2.next;
        }
        while (h1 != null) {
            int tempNum = h1.val + (isCarry ? 1 : 0);
            if (tempNum >= 10) {
                temp.next = new ListNode(tempNum - 10);
                isCarry = true;
            } else {
                temp.next = new ListNode(tempNum);
                isCarry = false;
            }
            temp = temp.next;
            h1 = h1.next;
        }

        while (h2 != null) {
            int tempNum = h2.val + (isCarry ? 1 : 0);
            if (tempNum >= 10) {
                temp.next = new ListNode(tempNum - 10);
                isCarry = true;
            } else {
                temp.next = new ListNode(tempNum);
                isCarry = false;
            }
            temp = temp.next;
            h2 = h2.next;
        }
        if (isCarry) {
            temp.next = new ListNode(1);
        }
        return head;
    }

}
