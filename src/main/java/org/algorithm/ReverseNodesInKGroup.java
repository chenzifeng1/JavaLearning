package org.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * <p>
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * <p>
 * <p>
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 * @date 2024/1/16 8:34 PM
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
//        list.add(6);
        ListNode head = ListNode.getList(list);

        ListNode process = process(head, 3);
        ListNode.sout(process);


    }


    public static ListNode process(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preHead = new ListNode(0, head);
        ListNode tail = preHead;
        ListNode pre = preHead;
        ListNode now = head;
        while (tail != null) {
            boolean isReverse = true;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    isReverse = false;
                    break;
                }
            }
            if (isReverse) {
                ListNode temp = now;
                ListNode next=temp.next;
                while (temp != tail) {
                    ListNode tempNex = next.next;
                    next.next = temp;
                    temp = next;
                    next = tempNex;
                }
                now.next = next;
                pre.next = tail;
                tail = now;
                pre = now;
                now = now.next;
            }

        }
        return preHead.next;
    }
}
