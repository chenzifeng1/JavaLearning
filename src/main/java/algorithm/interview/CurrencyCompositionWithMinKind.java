package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 * <p>
 * 该问题求的是最少使用多少张货币可以组成aim
 * @date 2023/12/20 6:48 PM
 */
public class CurrencyCompositionWithMinKind {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 5, 8};
        int aim = 9;
        int result = process(arr, 0, aim);
        int result1 = process1(arr, aim);
        int result2 = process2(arr, aim);
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);

    }


    /**
     * 从index到length位置，组成rest数值，最少使用多少张货币
     *
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            // 越界，返回0
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i * arr[index] <= rest; i++) {
            int temp = process(arr, index + 1, rest - i * arr[index]);
            if (temp != Integer.MAX_VALUE) {
                temp += i;
            } else {
                continue;
            }
            if (min > temp) {
                min = temp;
            }
        }
        return min;
    }


    public static int process1(int[] arr, int aim) {
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];

        for (int i = 1; i <= aim; i++) {
            dp[length][i] = Integer.MAX_VALUE;
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k * arr[i] <= j; k++) {
                    int temp = dp[i + 1][j - k * arr[i]];
                    if (temp != Integer.MAX_VALUE) {
                        temp += k;
                    } else {
                        continue;
                    }
                    if (min > temp) {
                        min = temp;
                    }
                }
                dp[i][j] = min;
            }
        }
        return dp[0][aim];
    }

    public static int process2(int[] arr, int aim) {
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[length][i] = Integer.MAX_VALUE;
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int ans;
                if (j - arr[i] >= 0) {
                    int temp = dp[i][j - arr[i]] != Integer.MAX_VALUE ? dp[i][j - arr[i]] + 1 : Integer.MAX_VALUE;
                    ans = Math.min(temp, dp[i + 1][j]);
                } else {
                    ans = dp[i + 1][j];
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][aim];
    }


}
