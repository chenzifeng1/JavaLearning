package org.algorithm;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续
 * 子数组
 * （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 测试用例的答案是一个 32-位 整数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 * @date 2024/3/7 11:51 PM
 */
public class MaximumProductSubarray {

    public static int max = 0;

    public static void main(String[] args) {
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        int[] nums = {2,3,-2,4};
//        System.out.println(maximumProductSubarray.maxProduct(nums));
        int dp = maximumProductSubarray.dp(nums);
        System.out.println(dp);
    }

    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        process(nums, 0, 1);
        return max;
    }


    /**
     * 取得是 从index-n的最大乘积
     *
     * @param nums
     * @param index
     * @return
     */
    public void process(int[] nums, int index, int sum) {
        if (index == nums.length) {
            return;
        }
        // 要当前的数
        max = Math.max(sum * nums[index], max);
        process(nums, index + 1, sum * nums[index]);
        // 不要当前的数
        process(nums, index + 1, 1);
    }


    public void process1(int[] nums, int index, int sum, Map<Integer, Integer> map) {
        if (index == nums.length) {
            return;
        }




    }


    public int dp(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int result = Integer.MIN_VALUE;
        int max = 1;
        int min = 1;
        for (int num : nums) {
            if (num < 0) {
                // 交换 最大数和最小数
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(num, max * num);
            min = Math.min(num, min * num);

            result = Math.max(result, max);
        }

        return result;
    }


}
