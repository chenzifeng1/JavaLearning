package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 接雨水 ：https://leetcode.cn/problems/trapping-rain-water/?envType=study-plan-v2&envId=top-100-liked
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 * @date 2023/12/25 1:35 PM
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int process = process(height);
        System.out.println(process);

    }

    /**
     * 最大前后缀高度法
     *
     * @param height
     * @return
     */
    public static int process(int[] height) {
        int length = height.length;
        if (length <= 2) {
            return 0;
        }
        int[] pre = new int[length];
        int[] suffix = new int[length];
        pre[0] = height[0];
        // 计算最大前缀数组
        for (int i = 1; i < length; i++) {
            pre[i] = Math.max(pre[i - 1], height[i]);
        }
        // 计算最大后缀数组
        suffix[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }
        int res = 0;
        for (int i = 0; i < length; i++) {
            res += Math.min(pre[i], suffix[i]) - height[i];
        }
        return res;
    }


    public static int back(int[] height) {
        // fixme 有问题
        int length = height.length;
        int result = 0;
        // 双指针
        int l = 0, r = l + 1;
        int temp = 0;
        for (; r < length; r++) {

            // fixme 还得注意一下，另一头能不能接住
            if (height[r] >= height[l]) {
                result += temp;
                l = r;
                temp = 0;
            } else {
                temp += (height[l] - height[r]);

            }
        }
        return result;
    }
}
