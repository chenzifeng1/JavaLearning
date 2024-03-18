package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 * @date 2024/3/13 3:09 PM
 */
public class EditDistance {


    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        String word1 = "horse";
        String word2 = "ros";

        int result = editDistance.minDistance(word1, word2);
        int result1 = editDistance.dp(word1, word2);
        System.out.println(result);
        System.out.println(result1);
    }

    public int minDistance(String word1, String word2) {
        return process(word1, word2, word1.length(), word2.length());
    }


    /**
     * 将word1的0-i位置上的字符串 改成word2的0-j位置的字符串所需要进行的最小步数
     *
     * @param word1
     * @param word2
     * @param i
     * @param j
     * @return
     */
    public int process(String word1, String word2, int i, int j) {
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }
        // 如果word1[i] 和 word2[j] 匹配上了，那么就看一下 i-1 到j-1到最小变换情况

        // 通过删除一个
        int p1 = process(word1, word2, i - 1, j);
        // 通过增加一个
        int p2 = process(word1, word2, i, j - 1);
        // 通过替换一个
        int p3 = process(word1, word2, i - 1, j - 1);

        int result = Math.min(p1, Math.min(p2, p3)) + 1;
        // 这里描述一下为啥是-1， 假如说 abad 和aqac 如果在 i-1和j-1的位置相等，
        // 那么其实该位置的替换可以被省略，但是得确保省略这个操作是最优的解法
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            result = Math.min(result, p3);
        }

        return result;
    }

    public int dp(String word1, String word2) {
        if (word1.length() == 0) {
            return word2.length();
        } else if (word2.length() == 0) {
            return word1.length();
        }

        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }

            }
        }

        return dp[n][m];

    }
}
