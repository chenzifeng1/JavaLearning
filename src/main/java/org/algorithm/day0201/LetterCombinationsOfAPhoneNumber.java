package org.algorithm.day0201;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 1
 * 2 abc
 * 3 def
 * 4 ghi
 * 5 jkl
 * 6 mno
 * 7 pqrs
 * 8 tuv
 * 9 wxyz
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 * @date 2024/2/1 10:32 AM
 */
public class LetterCombinationsOfAPhoneNumber {
    List<String> result = new ArrayList<>();

    Map<Character, char[]> map = new HashMap<>();

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber letterCombinationsOfAPhoneNumber = new LetterCombinationsOfAPhoneNumber();
        letterCombinationsOfAPhoneNumber.letterCombinations("234");
        for (String s : letterCombinationsOfAPhoneNumber.result) {
            System.out.println(s);
        }

    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }


        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        letterCombinations1(digits.toCharArray(), 0, new StringBuilder());

        return result;
    }

    public void letterCombinations1(char[] digits, int index, StringBuilder str) {
        if (index == digits.length) {
            result.add(str.toString());
            return;
        }
        char letter = digits[index];
        char[] chars = map.get(letter);
        for (char aChar : chars) {
            str.append(aChar);
            letterCombinations1(digits, index + 1, str);
            str.deleteCharAt(index);
        }
    }

}
