package org.algorithm.day0206;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * @date 2024/2/23 11:00 AM
 */
public class TopKFrequentElements {


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 2, 1};
        int k = 2;
        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
        int[] result = topKFrequentElements.topKFrequent(nums, k);
        for (int i : result) {
            System.out.println(i);
        }

    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Item> heap = new PriorityQueue<>((a, b) -> b.count - a.count);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.add(new Item(entry.getKey(), entry.getValue()));
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll().key;
        }

        return result;
    }


    public static class Item {
        int key;
        int count;

        public Item(int key, int count) {
            this.key = key;
            this.count = count;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

}
