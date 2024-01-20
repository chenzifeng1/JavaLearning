package org.algorithm;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 * @date 2024/1/5 10:29 AM
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] integers = maxSlidingWindow(nums, k);
        int[] integers1 = maxSlidingWindow1(nums, k);

        List<Integer> collect = IntStream.of(integers).boxed().collect(Collectors.toList());
        List<Integer> collect1 = IntStream.of(integers1).boxed().collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect1);
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        int max = Integer.MIN_VALUE;
        int[] result;
        if (length <= k) {
            result = new int[1];
        } else {
            result = new int[length - k + 1];
        }

        int windows = Math.min(length, k);

        for (int i = 0; i < windows; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        result[0] = max;
        int index = 1;
        if (windows == length) {
            return result;
        }

        int left = 0, right = k;
        while (right < length) {
            // 窗口右边入队
            int temp = nums[right];
            // 窗口左边出队
            int leftTemp = nums[left];
            if (leftTemp == max) {
                int tempMax = Integer.MIN_VALUE;
                // 如果左边出队的是最大的，那么重新找一下
                for (int i = left + 1; i <= right; i++) {
                    if (tempMax < nums[i]) {
                        tempMax = nums[i];
                    }
                }
                max = tempMax;
            } else {
                max = Math.max(max, temp);
            }
            result[index++] = max;
            right++;
            left++;
        }
        return result;
    }


    public static int[] maxSlidingWindow1(int[] nums, int k) {
        int length = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0] == 0 ? b[1] - a[1] : b[0] - a[0]);
        int[] result = new int[length - k + 1];
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        int[] peek = queue.peek();
        result[0] = peek[0];
        int index = 1;

        for (int i = k; i < length; i++) {
            // 右边元素入堆
            queue.offer(new int[]{nums[i], i});
            // 左边元素出堆
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            result[index++] = queue.peek()[0];
        }
        return result;
    }


}
