package org.algorithm.day0206;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * @date 2024/2/27 8:06 PM
 */
public class HouseRobber {

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = new int[]{1,2,1,1};
        int result = houseRobber.rob(nums);
        int result1 = houseRobber.rob1(nums);
        System.out.println(result);
        System.out.println(result1);
    }

    public int rob(int[] nums) {
        return doProcess(nums, 0);
    }

    /**
     * 从rest 到 length 位置的数字，偷取的最大金额
     *
     * @param nums
     * @param rest
     * @return
     */
    public int doProcess(int[] nums, int rest) {
        // 不能偷了
        if (rest >= nums.length) {
            return 0;
        }
        // 偷当前这家,但是就不能偷下一家了
        int p1 = nums[rest] + doProcess(nums, rest + 2);
        // 不偷当前这家，可以偷下一家
        int p2 = doProcess(nums, rest + 1);
        return Math.max(p1, p2);
    }

    public int rob1(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length + 1];
        for (int i = length - 1; i >= 0; i--) {
            if (i + 2 <= length) {
                int p1 = nums[i] + dp[i + 2];
                int p2 = dp[i + 1];
                dp[i] = Math.max(p1, p2);
            } else {
                dp[i] = nums[i];
            }

        }
        return dp[0];
    }

}
