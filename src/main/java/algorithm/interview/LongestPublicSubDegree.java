package algorithm.interview;

/**
 * @author chenzifeng
 * @version 1.0
 * 题目说明：
 * 给定两个字符串，求两个字符串的最长公共子串的长度。
 * 例如：
 * 输入：s1 = "abcd", s2 = "acd"
 * 输出：3
 * 解释：最长公共子串为 "acd"
 * @description
 * @date 2023/11/29 6:58 PM
 */
public class LongestPublicSubDegree {


    public static void main(String[] args) {
        String s1 = "000";
        String s2 = "000000000";
        Integer result = longestPublicSubDegree(s1, s2);
        Integer result1 = longestPublicSubDegree1(s1, s2);
        System.out.println("result: " + result);
        System.out.println("result1: " + result1);

    }

    public static Integer longestPublicSubDegree1(String s1, String s2) {
        if (null == s1 || null == s2 || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();

        int n = s1.length();
        int m = s2.length();
        // dp[i][j] 表示 s1[0...i]和s2[0...j] 在这个区间内，两者最长公共子串的长度
        // i,j的取值范围0-n-1,0-m-1
        int[][] dp = new int[n][m];
        // 分析：dp[i][j] 表示 s1[0...i] s2[0...j] 在这个区间内，两者最长公共子串的长度
        // 当i=0时，dp[0][j] = s1[0]==s2[j]?1:dp[0][j-1] 其中j-1 >0 dp[0][0] =
        //
        dp[0][0] = s1CharArray[0] == s2CharArray[0] ? 1 : 0;
        // 第一列 初始化
        for (int i = 1; i < m; i++) {
            dp[0][i] = s1CharArray[0] == s2CharArray[i] ? 1 : dp[0][i - 1];
        }
        // 第一行 初始化
        for (int i = 1; i < n; i++) {
            dp[i][0] = s1CharArray[i] == s2CharArray[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i][j - 1],
                        Math.max(dp[i - 1][j],
                                s1CharArray[i] == s2CharArray[j] ? (1 + dp[i - 1][j - 1]) : 0));
            }
        }

        return dp[n-1][m-1];

    }


    public static Integer longestPublicSubDegree(String s1, String s2) {
        if (null == s1 || null == s2 || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();

        int n1 = s1.length();
        int n2 = s2.length();

        return process1(s1CharArray, s2CharArray, n1 - 1, n2 - 1);

    }

    public static Integer process1(char[] s1, char[] s2, int i, int j) {
        if (i == 0 && j == 0) {
            return s1[i] == s2[j] ? 1 : 0;
        } else if (i == 0) {
            if (s1[i] == s2[j]) {
                return 1;
            } else {
                return process1(s1, s2, i, j - 1);
            }
        } else if (j == 0) {
            if (s1[i] == s2[j]) {
                return 1;
            } else {
                return process1(s1, s2, i - 1, j);
            }
        } else {
            // 如果i和j 都不等0
            int p1 = process1(s1, s2, i - 1, j);
            int p2 = process1(s1, s2, i, j - 1);
            int p3 = s1[i] == s2[j] ? (1 + process1(s1, s2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }


    }

}
