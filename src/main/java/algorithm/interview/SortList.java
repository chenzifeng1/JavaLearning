package org.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 * <p>
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * @date 2024/1/17 8:45 PM
 */
public class SortList {


    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(-1);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(3);
        ListNode node8 = new ListNode(7);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;



        ListNode sort = sort(head, null);

        while (sort != null) {
            System.out.println(sort.val);
            sort = sort.next;
        }
    }


    @Deprecated
    public static ListNode process1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        list.sort(Comparator.comparingInt(a -> a));
        temp = head;
        int index = 0;
        while (temp != null) {
            temp.val = list.get(index++);
            temp = temp.next;
        }
        return head;
    }

    /**
     * 递归 完成now节点以后的排序任务，并将now节点链到pre后面
     *
     * @param head
     * @param tail
     * @return
     */
    public static ListNode sort(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode p1 = sort(head, mid);
        ListNode p2 = sort(mid, tail);
        return merge(p1, p2);
    }


    public static ListNode merge(ListNode p1, ListNode p2) {
        ListNode preHead = new ListNode(-1);
        ListNode temp = preHead;
        ListNode pTemp1 = p1;
        ListNode pTemp2 = p2;
        while (pTemp1 != null && pTemp2 != null) {
            if (pTemp1.val < pTemp2.val) {
                temp.next = pTemp1;
                pTemp1 = pTemp1.next;
            } else {
                temp.next = pTemp2;
                pTemp2 = pTemp2.next;
            }
            temp = temp.next;
        }
        if (pTemp1 != null) {
            temp.next = pTemp1;
        } else {
            temp.next = pTemp2;
        }

        return preHead.next;
    }


}
