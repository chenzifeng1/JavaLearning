package org.algorithm.day0206;

import java.util.Map;
import java.util.Stack;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * @date 2024/2/13 10:40 AM
 */
public class ValidParentheses {


    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("()"));
        System.out.println(validParentheses.isValid("()[]{}"));
        System.out.println(validParentheses.isValid("(12313)]"));

    }

    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArray = s.toCharArray();

        return false;
    }



    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = Map.of(')', '(', '}', '{', ']', '[');
        stack.push('#');
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '(' || charArray[i] == '[' || charArray[i] == '{') {
                // 右括号压栈
                stack.push(charArray[i]);
            } else if (charArray[i] == ')' || charArray[i] == ']' || charArray[i] == '}') {
                // 左括号出栈
                Character pop = stack.pop();
                if (pop == '#' ||!pop.equals(map.get(charArray[i]))) {
                    return false;
                }
            }
        }
        return stack.size()==1;
    }


}
