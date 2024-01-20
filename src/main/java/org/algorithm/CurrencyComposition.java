package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr={1,1,1},aim=2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 * @description
 * @date 2023/12/14 10:13 AM
 */
public class CurrencyComposition {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 50, 1};
        int aim = 5;

        int process = process(arr, 0, aim);
        int process1 = process1(arr, aim);
        System.out.println(process);
        System.out.println(process1);

    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            // base case 1
            return rest == 0 ? 1 : 0;
        }
        if (rest < 0) {
            return 0;
        }


        // 选择这张货币 则获取的最大
        int p1 = process(arr, index + 1, rest - arr[index]);
        int p2 = process(arr, index + 1, rest);
        return p1 + p2;
    }

    public static int process1(int[] arr, int aim) {
        int length = arr.length;
        if (length == 0) {
            return 0;
        }
        int[][] dp = new int[length + 1][aim + 1];
        for (int i = 0 ;i <= length; i++) {
            dp[i][0] = 1;
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = 1; j <= aim; j++) {
                int p1 = dp[i + 1][j];
                int p2 = j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0;
                dp[i][j] = p1 + p2;
            }
        }

        return dp[0][aim];
    }

}
