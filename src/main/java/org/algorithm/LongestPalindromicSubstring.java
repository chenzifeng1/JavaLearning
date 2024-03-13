package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个字符串 s，找到 s 中最长的回文
 * 子串
 * 。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 * @date 2024/3/12 10:21 AM
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String str = "bababa";

        String result = longestPalindromicSubstring.dp(str);
        System.out.println(result);

    }


    /**
     * @return
     */
    public String dp(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        boolean[][] flag = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            // 每个单个字符都是一个回文串
            flag[i][i] = true;
        }

        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (j == i + 1) {
                    flag[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    flag[i][j] = s.charAt(i) == s.charAt(j) && flag[i + 1][j - 1];
                }
            }
        }
        int l = 0, r = 0;
        int maxLength = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (flag[i][j]) {
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        l = i;
                        r = j;
                    }
                }
            }

        }
        return s.substring(l, r + 1);
    }


}
