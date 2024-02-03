package org.algorithm.day0202;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chenzifeng
 * @version 1.0 括号生成
 * https://leetcode.cn/problems/generate-parentheses/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 8
 * @description
 * @date 2024/2/2 11:06 AM
 */
public class GenerateParentheses {

    List<String> result = new ArrayList<>();

    List<String> array = new ArrayList<>();

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> strings = generateParentheses.generateParenthesis(3);
        for (String string : strings) {
            System.out.println(string);
        }

    }

    public List<String> generateParenthesis(int n) {
        generate(n, new StringBuilder(), 0, 0);
        return result;
    }
    public void generate(int n, StringBuilder stringBuilder, int open, int close) {
        if (stringBuilder.length() == 2 * n) {
            result.add(stringBuilder.toString());
            return;
        }
        if (open < n) {
            stringBuilder.append('(');
            generate(n, stringBuilder, open + 1, close);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        if (close < open) {
            stringBuilder.append(')');
            generate(n, stringBuilder, open, close + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }




}
