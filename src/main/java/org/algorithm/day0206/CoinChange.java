package org.algorithm.day0206;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 * @date 2024/2/28 4:01 PM
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
//        int result = coinChange.coinChange(coins, amount);
        int result1 = coinChange.coinChange1(coins, amount);
//        System.out.println(result);
        System.out.println(result1);
    }

    public int coinChange(int[] coins, int amount) {
        int result = doProcess(coins, 0, amount);
        return result == Integer.MAX_VALUE ? -1 : result;

    }

    public int doProcess(int[] coins, int index, int rest) {
        if (rest == 0) {
            return 0;
        } else if (index >= coins.length) {
            return Integer.MAX_VALUE;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i * coins[index] <= rest; i++) {
            int nextResult = doProcess(coins, index + 1, rest - i * coins[index]);
            if (nextResult != Integer.MAX_VALUE) {
                result = Math.min(result, nextResult + i);
            }
        }
        return result;
    }


    public int coinChange1(int[] coins, int amount) {
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[length][i] = Integer.MAX_VALUE;
        }
        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i + 1][j]);
                } else {
                    dp[i][j] = dp[i + 1][j];
                }

            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

}
