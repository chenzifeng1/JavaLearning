package org.algorithm.day0206;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 * @date 2024/2/26 7:57 PM
 */
public class JumpGame {

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums = {1};
        boolean result = jumpGame.canJump(nums);
        boolean result1 = jumpGame.canJump1(nums);
        boolean result2 = jumpGame.canJump2(nums);
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
    }

    public boolean canJump(int[] nums) {
        return doProcess(nums, 0);
    }

    private boolean doProcess(int[] nums, int index) {
        int length = nums.length;
        if (index == length - 1) {
            // 这一步到终点了，返回true
            return true;
        }
        // 没到终点,但是当前位置步数为0了
        if (nums[index] == 0) {
            return false;
        }
        boolean result = false;
        for (int i = 1; i <= nums[index]; i++) {
            if (index + i <= length - 1) {
                result = doProcess(nums, index + i);
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    public boolean canJump1(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return true;
        }
        boolean[] dp = new boolean[length ];

        dp[length-1] = true;
        for (int i = length - 2; i >= 0; i--) {
            for (int k = 0; k <= nums[i]; k++) {
                if (i + k <length) {
                    dp[i] = dp[i + k];
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }
        return dp[0];
    }

    public boolean canJump2(int[] nums) {
        int length = nums.length;
        int k = 0;

        for (int i = 0; i < length; i++) {
            if (i > k) return false;
            k = Math.max(k, i+nums[i]);
        }
        return true;
    }


}
