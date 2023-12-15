package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，值不重复
 * 每张货币默认是无数张
 * 返回组成aim的方法数
 * @description
 * @date 2023/12/14 10:13 AM
 */
public class CurrencyCompositionNoLimit {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 50, 2,3,5,10};
        int aim = 98;

        int process = process(arr, 0, aim);
        int process1 = process1(arr, aim);
        int process2 = process2(arr, aim);
        System.out.println(process);
        System.out.println(process1);
        System.out.println(process2);

    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int num = 0; num * arr[index] <= rest; num++) {
            ways += process(arr, index + 1, rest - (num * arr[index]));
        }
        return ways;
    }

    public static int process1(int[] arr, int aim) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;

        for (int i = n - 1; i >= 0; i--) {

            for (int j = 0; j <= aim; j++) {
                int ways = 0;
                for (int num = 0; num * arr[i] <= j; num++) {
                    int sum = num * arr[i];
                    ways +=  dp[i + 1][j - sum];
                }
                dp[i][j] = ways;
            }
        }
        return dp[0][aim];
    }


    public static int process2(int[] arr, int aim) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;

        for (int i = n - 1; i >= 0; i--) {

            for (int j = 0; j <= aim; j++) {
                int ways = 0;
                if (j-arr[i] >=0) {
                    ways += dp[i][j-arr[i]];
                }
                ways += dp[i+1][j];
                dp[i][j] = ways;
            }
        }
        return dp[0][aim];
    }

}
