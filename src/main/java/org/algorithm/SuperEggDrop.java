package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * <p>
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * <p>
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 1, n = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
 * 如果它没碎，那么肯定能得出 f = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
 * 示例 2：
 * <p>
 * 输入：k = 2, n = 6
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：k = 3, n = 14
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= 100
 * 1 <= n <= 104
 * @date 2024/1/9 12:12 AM
 */
public class SuperEggDrop {

    public static void main(String[] args) {
        int n = 9;
        int k = 2;
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        int process = process2(k, n, dp);

        System.out.println(process);

    }

    /**
     * 折半不一定是最优解
     *
     * @param k
     * @param left
     * @param right
     * @return
     */
    @Deprecated
    private static int process0(int k, int left, int right) {
        if (k == 1) {
            // 如果鸡蛋还剩一个，只能一楼楼尝试了
            return right - left + 1;
        }
        if (left == right) {
            // 定位到临界楼层
            return 1;
        }

        // 如果鸡蛋还有多个，可以折半尝试
        int mid = left + ((right - left) >> 1);

        // 鸡蛋碎了
        int p1;
        if (left <= mid - 1) {
            p1 = process0(k - 1, left, mid - 1);
        } else {
            p1 = 1;
        }

        int p2;
        if (mid + 1 <= right) {
            p2 = process0(k, mid + 1, right);
        } else {
            p2 = 1;
        }

        return Math.min(p1, p2) + 1;
    }


    private static int process1(int k, int n, int[][] map) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (map[k][n] != -1) {
            return map[k][n];
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            // 碎了
            int p1 = process1(k - 1, i - 1, map);
            // 没碎 剩余楼层为 n-i
            int p2 = process1(k, n - i, map);
            int temp = Math.max(p1, p2);
            res = Math.min(res, temp + 1);
        }
        map[k][n] = res;

        return res;
    }

    private static int process2(int k, int n, int[][] map) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (map[k][n] != -1) {
            return map[k][n];
        }

        int res = Integer.MAX_VALUE;
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 碎了
            int p1 = process2(k - 1, mid - 1, map);
            //没碎
            int p2 = process2(k, n - mid, map);
            // 对mid层楼的结果一定在 结果大的一方
            if (p1 > p2) {
                right = mid - 1;
                res = Math.min(res, p1+1);
            } else {
                left = mid + 1;
                res = Math.min(res, p2+1);
            }
        }
        map[k][n] = res;

        return res;
    }


    public int superEggDrop(int k, int n) {
        // Right now, dp[i] represents dp(1, i)
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            dp[i] = i;
        }

        for (int j = 2; j <= k; ++j) {
            // Now, we will develop dp2[i] = dp(j, i)
            int[] dp2 = new int[n + 1];
            int x = 1;
            for (int m = 1; m <= n; ++m) {
                // Let's find dp2[m] = dp(j, m)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[m-x]) > max(dp[x], dp2[m-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < m && Math.max(dp[x - 1], dp2[m - x]) > Math.max(dp[x], dp2[m - x - 1])) {
                    x++;
                }

                // The final answer happens at this x.
                dp2[m] = 1 + Math.max(dp[x - 1], dp2[m - x]);
            }

            dp = dp2;
        }

        return dp[n];
    }

    public int superEggDrop1(int K, int N) {
        if (K < 1 || N < 1) return 0;
        int[] dp = new int[K];
        int res = 0;
        while (true) {
            res++;
            int pre = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + pre + 1;
                pre = tmp;
                if (dp[i] >= N) {
                    return res;
                }
            }
        }
    }


}
