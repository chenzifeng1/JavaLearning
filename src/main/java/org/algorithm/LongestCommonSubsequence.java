package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * 提示：
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 * @date 2024/3/13 2:19 PM
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        String text1 = "abcde";
        String text2 = "ace";
        int result = longestCommonSubsequence.dp(text1, text2);
        int result1 = longestCommonSubsequence.longestCommonSubsequence(text1, text2);
        System.out.println(result);
        System.out.println(result1);

    }

    public int longestCommonSubsequence(String text1, String text2) {
        return process(text1, text2, 0, 0);
    }

    /**
     * 保证text1为
     *
     * @param text1
     * @param text2
     * @return
     */
    public int process(String text1, String text2, int i, int j) {
        if (text1.length() == i || text2.length() == j) {
            return 0;
        }
        // 判断text1[i]和text2[j]的情况
        int p = 0;
        if (text1.charAt(i) == text2.charAt(j)) {
            p = 1 + process(text1, text2, i + 1, j + 1);
        } else {
            p = Math.max(process(text1, text2, i + 1, j), process(text1, text2, i, j + 1));
        }
        return p;
    }

    public int dp(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[0][0];
    }

}
