package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * 给定一个二维数组matrix,一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 * @description
 * @date 2023/12/12 8:55 PM
 */
public class MinimumistanceAccumulationSum {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int process = process1(matrix, matrix.length, matrix[0].length);
        System.out.println(process);
    }


    /**
     * 从（x,y）位置到（n，m）位置的最小距离累加和
     *
     * @param matrix
     * @param n
     * @param m
     * @param x
     * @param y
     * @return
     */
    public static int process(int[][] matrix, int n, int m, int x, int y) {
        if (x == n - 1 && y == m - 1) {
            // base case 走到终点
            return matrix[x][y];
        }
        if (x >= n || y >= m) {
            return 0;
        }
        // 往下走
        int p1 = process(matrix, n, m, x + 1, y);
        // 往右走
        int p2 = process(matrix, n, m, x, y + 1);

        return Math.min(p1, p2) + matrix[x][y];
    }


    public static int process1(int[][] matrix, int n, int m) {
        int[][] dp = new int[n][m];

        dp[n - 1][m - 1] = matrix[n - 1][m - 1];
        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1][i] = matrix[n - 1][i] + dp[n - 1][i + 1];
        }
        for (int i = n-2; i >=0;i--) {
            dp[i][m-1] = matrix[i][m-1] + dp[i+1][m-1];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        return dp[0][0];
    }

}
