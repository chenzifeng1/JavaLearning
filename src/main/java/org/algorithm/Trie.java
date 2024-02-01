package org.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzifeng
 * @version 1.0
 * @description Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 * @date 2024/1/31 8:13 PM
 */
public class Trie {

    Map<Character, TrieNode> head;

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }


    public Trie() {
        head = new HashMap<>();
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        char[] charArray = word.toCharArray();
        char firstChar = charArray[0];

        TrieNode firstNode = head.getOrDefault(firstChar, new TrieNode(firstChar));
        head.put(firstChar, firstNode);
        insertOneChar(charArray, 1, firstNode);
    }


    private void insertOneChar(char[] charArray, int index, TrieNode node) {
        if (index == charArray.length) {
            node.setEnd(true);
            return;
        }
        char c = charArray[index];
        TrieNode next = node.getSuffix().getOrDefault(c, new TrieNode(c));
        insertOneChar(charArray, index + 1, next);
        node.getSuffix().put(c, next);
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char[] charArray = word.toCharArray();
        char c = charArray[0];
        if (!head.containsKey(c)) {
            return false;
        }

        TrieNode trieNode = head.get(c);
        for (int i = 1; i < charArray.length; i++) {
            if (trieNode.getSuffix().containsKey(charArray[i])) {
                trieNode = trieNode.getSuffix().get(charArray[i]);
            }else {
                return false;
            }

        }

        return trieNode.isEnd;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        char[] charArray = prefix.toCharArray();
        char c = charArray[0];
        if (!head.containsKey(c)) {
            return false;
        }

        TrieNode trieNode = head.get(c);
        for (int i = 1; i < charArray.length; i++) {
            if (trieNode.getSuffix().containsKey(charArray[i])) {
                trieNode = trieNode.getSuffix().get(charArray[i]);
            }else {
                return false;
            }

        }

        return true;

    }


    public static class TrieNode {

        boolean isEnd = false;
        char val;

        Map<Character, TrieNode> suffix;

        public TrieNode(char val) {
            this.val = val;
            suffix = new HashMap<>();
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public char getVal() {
            return val;
        }

        public void setVal(char val) {
            this.val = val;
        }

        public Map<Character, TrieNode> getSuffix() {
            return suffix;
        }

        public void setSuffix(Map<Character, TrieNode> suffix) {
            this.suffix = suffix;
        }
    }

}
