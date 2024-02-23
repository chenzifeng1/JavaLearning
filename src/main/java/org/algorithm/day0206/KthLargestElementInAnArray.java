package org.algorithm.day0206;

import java.util.PriorityQueue;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * @date 2024/2/21 3:49 PM
 */
public class KthLargestElementInAnArray {
    int[] heap;
    int heapSize = 0;

    public static void main(String[] args) {
        KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        int kthLargest = kthLargestElementInAnArray.findKthLargest1(nums, k);
        System.out.println(kthLargest);

    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            queue.add(num);
        }
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = queue.poll();
        }
        return result;
    }


    /**
     * 自己创建堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        heap = nums.clone();
        for (int i = 0; i < heap.length; i++) {
            push(heap, i);
        }
        heapSize = heap.length - 1;
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = pop();
        }
        return result;
    }


    private int pop(){
        int result = heap[0];
        swap(heap, 0, heapSize);
        heapify(heap, 0, heapSize);
        heapSize--;
        return result;
    }


    private void heapify(int[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
            largest = heap[index] < heap[largest] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(heap, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void push(int[] heap, int heapSize) {
        // 找到最大值
        while (heap[heapSize] > heap[(heapSize - 1) / 2]) {
            swap(heap, heapSize, (heapSize - 1) / 2);
            heapSize = (heapSize - 1) / 2;
        }
    }


    private void swap(int[] heap, int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }


}
