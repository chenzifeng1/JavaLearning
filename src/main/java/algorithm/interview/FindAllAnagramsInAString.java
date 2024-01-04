package org.algorithm;

import java.util.*;

/**
 * @author chenzifeng
 * @version 1.0
 * @description https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * @date 2024/1/3 1:36 PM
 */
public class FindAllAnagramsInAString {

    public static void main(String[] args) {
//        String s = "cbaebabacd";
        String s = "abdc";
//        String p = "abc";
        String p = "abc";
//        List<Integer> process = findAnagrams(s, p);
        List<Integer> process1 = findAnagrams1(s, p);
//        System.out.println(process);
        System.out.println(process1);


    }


    /**
     * 逻辑有点问题
     * @param s
     * @param p
     * @return
     */
    @Deprecated
    public static List<Integer> findAnagrams1(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        char[] pCharArray = p.toCharArray();
        char[] sCharArray = s.toCharArray();
        for (char c : pCharArray) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        List<Integer> result = new ArrayList<>();
        while (right < s.length()) {
            char rChar = sCharArray[right];
            window.put(rChar, window.getOrDefault(rChar, 0) + 1);
            if (window.get(rChar).equals(need.get(rChar))) {
                // 如果发现某个字母数量匹配上了
                valid++;
            }

            while (right - left >= p.length()) {
                char c = sCharArray[left];
                if (valid == need.size()) {
                    result.add(left);
                }
                // windows移除left的元素
                if (need.containsKey(c)) {
                    valid--;
                }
                window.put(c, window.get(c) - 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
                left++;
            }
            right++;
        }

        return result;
    }


    public static List<Integer> findAnagrams(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        if (sl < pl) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();

        int[] sArray = new int[26];
        int[] pArray = new int[26];
        // 先将p长度的数据统计出来
        for (int i = 0; i < pl; i++) {
            ++sArray[s.charAt(i) - 'a'];
            ++pArray[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sArray, pArray)) {
            // 相同说明0-p长度的s满足要求
            ans.add(0);
        }

        for (int i = 0; i + pl < sl; i++) {
            --sArray[s.charAt(i) - 'a'];
            ++sArray[s.charAt(i + pl) - 'a'];
            if (Arrays.equals(sArray, pArray)) {
                // 这里将i移除之后满足了，所以满足的起始坐标应该是i+1
                ans.add(i+1);
            }
        }
        return ans;
    }

}
