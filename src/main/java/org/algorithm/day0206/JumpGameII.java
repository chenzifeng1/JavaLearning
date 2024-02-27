package org.algorithm.day0206;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * <p>
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * <p>
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * <p>
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 nums[n-1]
 * @date 2024/2/26 10:52 PM
 */
public class JumpGameII {

    public static void main(String[] args) {
//        int[] nums = {5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
        int[] nums = {2, 3, 1, 1, 4};
        JumpGameII jumpGameII = new JumpGameII();
        System.out.println(jumpGameII.jump2(nums));
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 1;
        }
        return doProcess(nums, 0);
    }

    /**
     * 从index 到 length 可以走到最小步数
     *
     * @param nums
     * @param index
     * @return
     */
    public int doProcess(int[] nums, int index) {
        if (index == nums.length) {
            return 0;
        }
        // 如果没有到最后
        if (index + nums[index] >= nums.length - 1) {
            return 1;
        } else {
            int result = Integer.MAX_VALUE;
            // 一个个测试
            for (int i = 1; i <= nums[index]; i++) {
                result = Math.min(result, doProcess(nums, index + i));
            }
            return (result == Integer.MAX_VALUE ? Integer.MAX_VALUE : result + 1);
        }
    }

    /**
     * dp方式
     *
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return 1;
        }
        int[] dp = new int[length];
        dp[length - 1] = 1;

        for (int i = length - 2; i >= 0; i--) {
            int num = nums[i];
            int result = Integer.MAX_VALUE;
            for (int j = 1; j <= num; j++) {
                if (i + j >= length - 1) {
                    dp[i] = 1;
                    result = 0;
                    break;
                }
                result = Math.min(result, dp[i + j]);
            }
            dp[i] = (result == Integer.MAX_VALUE ? Integer.MAX_VALUE : result + 1);
        }


        return dp[0];
    }

    public int jump2(int[] nums) {
        int start = 0, end = 1;
        int ans = 0;

        while (end < nums.length) {
            int temp = 0;
            for (int i = start; i < end; i++) {
                temp = Math.max(temp, i + nums[i]);
            }
            start = end;
            end = temp + 1;
            ans++;
        }
        return ans;
    }


}

