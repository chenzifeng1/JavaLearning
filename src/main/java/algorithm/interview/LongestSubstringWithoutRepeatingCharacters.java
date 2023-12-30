package org.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzifeng
 * @version 1.0
 * @description https://leetcode.cn/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-100-liked
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * @date 2023/12/25 10:57 AM
 */
public class LongestSubstringWithoutRepeatingCharacters {


    public static void main(String[] args) {
        String str = "abba";

        int process = process(str);
        int process1 = process1(str);
        System.out.println(process);
        System.out.println(process1);

    }


    public static int process(String str) {
        int length = str.length();
        if (length <= 1) {
            return length == 0 ? 0 : 1;
        }

        char[] charArray = str.toCharArray();

        Map<Character, Integer> indexMap = new HashMap<>(length);
        int max = 0;
        for (int i = 0; i < length; ) {
            indexMap.put(charArray[i], i);
            int next = i + 1;
            while (next < length && indexMap.get(charArray[next]) == null) {
                indexMap.put(charArray[next], next);
                next++;
            }
            // 不管是重复字符还是遍历到头了，此次获取到到不重复子串的长度为 next-i+1
            if (max < (next - i)) {
                max = next - i;
            }
            // 从重复字符前置的字符后一个开始遍历
            if (next < length) {
                i = indexMap.get(charArray[next]) + 1;
                indexMap.clear();
            } else {
                //
                break;
            }
        }

        return max;
    }

    public static int process1(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length;

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        Integer left = 0;
        for (int i = 0; i < length; i++) {

            if (map.get(charArray[i]) != null) {
                left = Math.max(left, map.get(charArray[i]) + 1) ;
            }
            // 更新该字符最靠后的位置
            map.put(charArray[i], i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }


}
