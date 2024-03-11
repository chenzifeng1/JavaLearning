package org.algorithm;

import java.util.Stack;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号
 * 子串
 * 的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 * @date 2024/3/10 10:55 PM
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        String s = "(()())";
        int result = longestValidParentheses.longestValidParentheses(s);
        System.out.println(result);


    }

    public int longestValidParentheses(String s) {
        char[] charArray = s.toCharArray();
        int length = s.length();
        int max = 0;
        // 以 i 为结尾的有效括号长度
        int[] dp = new int[length + 1];

        for (int i = 1; i < length; i++) {
            if (charArray[i] == ')') {
                if (charArray[i - 1] == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (dp[i - 1] > 0) {
                    if (i - dp[i - 1] - 1 >= 0 && charArray[i - dp[i - 1] - 1] == '(') {
                        // (()***()) 模式
                        dp[i] = dp[i - 1] + 2;
                        if (i - dp[i - 1] - 2 > 0) {
                            dp[i] = dp[i - dp[i - 1] - 2] + 2 + dp[i - 1];
                        }
                    }
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 计算从i-n可以返回的最大有效括号长度
     *
     * @param stack
     * @param index
     * @param chars
     * @return
     */
    public void process(Stack<Character> stack, int result, int index, char[] chars) {
        if (index == chars.length) {

            return;
        }
        if (chars[index] == '(') {
            stack.push('(');
            process(stack, result, index + 1, chars);
        } else {
            if (stack.isEmpty()) {
                // 匹配不上了,先记录当前的长度是否大于最大值，然后继续往后匹配
                process(stack, 0, index + 1, chars);
            } else {
                stack.pop();
                process(stack, result + 2, index + 1, chars);
            }
        }
    }
}
