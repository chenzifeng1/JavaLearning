package org.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzifeng
 * @version 1.0
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 * @description
 * @date 2024/1/15 1:40 PM
 */
public class LinkedListCycleII {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        System.out.println(process1(head));

    }

    public static ListNode process(ListNode head) {
        Map<ListNode, ListNode> map = new HashMap<>();
        ListNode temp = head;
        map.put(head, null);
        while (temp != null) {
            ListNode next = temp.next;
            if (map.containsKey(next)) {
                return next;
            } else {
                map.put(next, temp);
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 要求：控件复杂度为O(1)
     * https://leetcode.cn/problems/linked-list-cycle-ii/solutions/12616/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/?envType=study-plan-v2&envId=top-100-liked
     * 思路：
     *
     * @param head
     * @return
     */
    public static ListNode process1(ListNode head) {
        // 假设链的长度为 a + b，其中 b是环的长度，a为链表与环之间的距离
        // stepDistence 关系公式： fastStep = 2 * slowStep  fastStep - slowStep = n * b （b为环的节点数）
        // 推出 slowStep = n * b，
        // 此刻让fast 指向head，然后slow和fast同时一步步走，当fast和slow相遇时，相遇的节点就是入环的节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            if ( fast.next != null) {
                fast = fast.next.next;
                if (fast == slow) {
                    fast = head;
                    while (fast != slow) {
                        fast = fast.next;
                        slow = slow.next;
                    }
                    return slow;
                }
            } else {
                break;
            }
        }
        return null;
    }


    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }


}
