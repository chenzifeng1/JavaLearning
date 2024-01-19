package org.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzifeng
 * @version 1.0
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * @description
 * @date 2024/1/19 11:26 AM
 */
public class LRUCache {

    int capacity;
    Map<Integer, DeListNode> map = new HashMap<>();
    DeListNode head;

    DeListNode tail;


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 3);
        System.out.println(cache.get(2));
        cache.put(1, 3);
        cache.put(4, 4);
        System.out.println(cache.get(2));
//        cache.put(5, 5);
//        cache.put(6, 6);
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));


    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DeListNode(-1);
        tail = new DeListNode(-1);
        head.next = tail;
        tail.pre = head;

    }

    public int get(int key) {
        DeListNode node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            moveHead(node);
        }
        return node.val;

    }

    public void put(int key, int value) {
        DeListNode node = map.get(key);
        if (node == null) {
            node = new DeListNode(key, value);
            addToHead(node);
            map.put(key, node);
            if (map.size() > capacity) {
                DeListNode removeNode = removeTail();
                map.remove(removeNode.key);
            }

        } else {
            node.val = value;
            moveHead(node);
        }
    }

    private void moveHead(DeListNode node) {
        removeNode(node);
        addToHead(node);
    }


    private void addToHead(DeListNode node) {
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    private void removeNode(DeListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }


    private DeListNode removeTail() {
        DeListNode pre = tail.pre;
        removeNode(pre);
        return pre;
    }


}
