package org.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 560. 和为 K 的子数组
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 * @date 2024/1/4 7:00 PM
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int k = 0;
        int[] array = new int[]{-1, -1, 1};
        int process = process(array, k);
        int process1 = process(array, k);
        System.out.println(process);
        System.out.println(process1);
    }

    /**
     * 从 k 到 array.length位置 可以组成 rest 的连续子数组
     *
     * @param array
     * @param k
     * @return
     */
    public static int process(int[] array, int k) {
        int length = array.length;
        int left = 0, right = 0;
        int temp = 0;
        int result = 0;
        while (left < length) {
            temp = array[left];
            right = left + 1;
            while (right < length) {
                if (temp == k) {
                    result++;
                }
                temp += array[right++];
            }
            if (temp == k) {
                result++;
            }
            left++;
        }
        return result;
    }


    /**
     * 前缀和思路
     * 记录PRE[I]为0-i上的总和，那么如果要求 j-i上的子数组的和，则为 pre[i]-pre[j-1]
     * 按照题意，我们需要获取到pre[i]-pre[j-1]=k的情况，那么转换一下，则为pre[i]-k=pre[j-1]
     * 所以我们只要求pre[i]-k出现了几次，那么就说明以i位置为结尾的时候，可以有多少个满足要求的情况
     *
     * @param array
     * @param k
     * @return
     */
    public static int process1(int[] array, int k) {
        // KEY 前缀和的值 VALUE 前缀和为key的情况出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);
        int temp = 0;
        int result = 0;

        for (int i = 0; i < array.length; i++) {
            temp += array[i];
            if (map.get(temp-k) != null) {
                result += map.get(temp-k);
            }
            // 将前缀和放进去map里面
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        return result;

    }

}
