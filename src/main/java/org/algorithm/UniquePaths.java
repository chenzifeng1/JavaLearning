package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * 示例 1：
 * <p>
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 * <p>
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 * <p>
 * 输入：m = 3, n = 3
 * 输出：6
 * @date 2024/3/11 11:30 PM
 */
public class UniquePaths {

    public static void main(String[] args) {

        int m = 3;
        int n = 1;
        UniquePaths uniquePaths = new UniquePaths();

        int result = uniquePaths.uniquePaths(m, n);
        int result1= uniquePaths.uniquePaths1(m, n);
        System.out.println(result);
        System.out.println(result1);
    }


    public int uniquePaths(int m, int n) {
        return process(m, n, 1, 1);

    }

    /**
     * 从x，y位置走到m,n位置的走法
     *
     * @param m
     * @param n
     * @param x
     * @param y
     * @return
     */
    public int process(int m, int n, int x, int y) {
        if (x == m && y == n) {
            return 1;
        }
        if (x > m || y > n) {
            return 0;
        }
        // 往右走
        int p1 = process(m, n, x + 1, y);

        // 往下走
        int p2 = process(m, n, x, y + 1);
        return p1 + p2;
    }

    public int uniquePaths1(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[m][n] = 1;

        for (int i = m; i > 0; i--) {

            for (int j = n; j > 0; j--) {
                if (i==m && j ==n) {
                    continue;
                }
                int p1 = i + 1 > m ? 0 : dp[i + 1][j];
                int p2 = j + 1 > n ? 0 : dp[i][j + 1];
                dp[i][j] = p1 + p2;
            }
        }
        return dp[1][1];
    }


}
