package org.algorithm.day0203;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * @date 2024/2/3 11:11 AM
 */
public class PalindromePartitioning {

    List<List<String>> result = new ArrayList<>();


    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        List<List<String>> result = palindromePartitioning.partition("aaebcbeaa");
        for (List<String> strings : result) {
            System.out.println(strings);
        }
    }

    /**
     * 添加结果
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        boolean[][] flag = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(flag[i], true);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                flag[i][j] = chars[i] == chars[j] && flag[i + 1][j - 1];
            }
        }
        dfs(s, flag, 0, new ArrayList<>());
        return result;
    }

    public void dfs(String s, boolean[][] flag, int n, List<String> tempResult) {
        if (n == s.length()) {
            result.add(new ArrayList<>(tempResult));
            return;
        }
        // 以n为起点向s.length()进行探索

        for (int i = n; i < s.length(); i++) {
            if (flag[n][i]) {
                tempResult.add(s.substring(n, i + 1));
                dfs(s, flag, i + 1, tempResult);
                tempResult.remove(tempResult.size() - 1);
            }
        }

    }


    /**
     * bad way
     *
     * @param s
     * @return
     */
    public List<List<String>> partition1(String s) {
        doPartition(s, 0, new StringBuilder(), new ArrayList<>());
        return result;
    }

    public void doPartition(String s, int index, StringBuilder stringBuilder, List<String> tempList) {
        if (index == s.length()) {
            String string = stringBuilder.toString();
            if (isPalindrome(string) && isOk(s, tempList)) {
                result.add(new ArrayList<>(tempList));
            }
            // 最后无法分割
            return;
        }
        stringBuilder.append(s.charAt(index));
        String string = stringBuilder.toString();
        if (isPalindrome(string)) {
            // 采用当前分割
            tempList.add(stringBuilder.toString());
            doPartition(s, index + 1, new StringBuilder(), tempList);
            tempList.remove(tempList.size() - 1);
        }
        // 不采用当前分割
        doPartition(s, index + 1, stringBuilder, tempList);
    }

    boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    boolean isOk(String s, List<String> list) {
        int sum = 0;
        for (String str : list) {
            sum += str.length();
        }
        return sum == s.length();
    }


}
