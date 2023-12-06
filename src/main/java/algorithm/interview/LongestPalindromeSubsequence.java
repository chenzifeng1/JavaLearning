package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * 最长回文子序列 https://leetcode.cn/problems/longest-palindromic-subsequence/
 * 给你一个字符串 s，找到其中最长的回文子序列，并返回该长度。
 * 回文子序列：如果一个字符串重读和反着读是一致的，并且删除某些字符（可能是没有删除）后，仍然是同一个字符串，那么这个字符串
 * 就是一个回文子序列。例如，"ace" 是最长的回文子序列， "a","c", "e" 都是回文子序列。
 * 思路：一个字符串的正反串，求最长公共子序列
 * @description
 * @date 2023/12/5 9:34 PM
 */
public class LongestPalindromeSubsequence {

    public static void main(String[] args) {
        String s = "bbcab";
        System.out.println(process(s));
        System.out.println(process1(s));
        System.out.println(process2(s));
    }

    /**
     * 使用求两个字符串的最长公共子序列的方法，将字符串正反串，求最长回文子序列
     *
     * @param s
     * @return
     */
    public static Integer process(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        StringBuffer stringBuffer = new StringBuffer(s);
        String reverse = stringBuffer.reverse().toString();
        return LongestPublicSubDegree.longestPublicSubDegree1(s, reverse);
    }

    public static Integer process1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] charArray = s.toCharArray();

        return doProcess1(charArray, 0, n - 1);
    }

    /**
     * s[i,j]范围上最长回文子序列长度
     *
     * @param charArray
     * @param l
     * @param r
     * @return
     */
    public static Integer doProcess1(char[] charArray, int l, int r) {
        if (r > l) {
            return 0;
        }
        if (l == r) {
            return 1;
        }
        if (charArray[l] == charArray[r]) {
            return 2 + doProcess1(charArray, l + 1, r - 1);
        } else {
            int p1 = doProcess1(charArray, l + 1, r);
            int p2 = doProcess1(charArray, l, r - 1);
            return Math.max(p1, p2);
        }
    }


    public static Integer process2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] charArray = s.toCharArray();

        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    continue;
                }
                if (charArray[i] == charArray[j]) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    int p1 = dp[i][j - 1];
                    int p2 = dp[i + 1][j];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }

        return dp[0][n - 1];

    }

}
