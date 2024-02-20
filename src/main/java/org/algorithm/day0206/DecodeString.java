package org.algorithm.day0206;

import java.util.Stack;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * <p>
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * <p>
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 * @date 2024/2/15 9:55 AM
 */
public class DecodeString {

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        String s = "3[a2[c]]";
        String s1 = "abc3[cd]xyz";
        System.out.println(decodeString.decodeString(s));

    }

    // 递归过程
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        stack.push("#");
        StringBuilder result = new StringBuilder();
        for (char c : charArray) {
            // todo 这里可以优化为发现数字之后一直压栈，直到下一个不是数字的字符
            if (isNum(String.valueOf(c))) {
                if (isNum(stack.peek())) {
                    // 将数字拿出来组合
                    String pop = stack.pop();
                    String numStr = pop + c;
                    stack.push(numStr);
                }else {
                    stack.push(String.valueOf(c));
                }
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                while (!stack.peek().equals("[")) {
                    String pop = stack.pop();
                    temp.insert(0, pop);
                }
                // 弹出[
                stack.pop();
                // 弹出数字
                String numStr = stack.pop();
                int num = Integer.parseInt(numStr);
                StringBuilder tempResult = new StringBuilder();
                for (int i = 0; i < num; i++) {
                    tempResult.append(temp);
                }
                stack.push(tempResult.toString());
            } else {
                stack.push(String.valueOf(c));
            }
        }

        while (stack.peek() != "#") {
            result.insert(0, stack.pop());
        }


        return result.toString();
    }

    private boolean isNum(String str) {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (!(c - '0' >= 0) || !(c - '0' <= 9)) {
                return false;
            }
        }
        return true;
    }


}
