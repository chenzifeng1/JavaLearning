package org.algorithm.day0202;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例 1：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * @date 2024/2/2 4:47 PM
 */
public class WordSearch {

    Boolean flag = false;

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCESEEEFS";
        boolean exist = wordSearch.exist(board, word);
        System.out.println(exist);

    }


    public boolean exist(char[][] board, String word) {

        char firstChar = word.charAt(0);
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (board[i][j] == firstChar) {
                    explore(board, word, i, j, new int[m][n]);
                }
                if (flag) {
                    return true;
                }
            }
        }
        return flag;

    }

    public void explore(char[][] board, String word, int x, int y, int[][] sign) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        if (sign[x][y] == 1) {
            return;
        }

        if (word.length() == 0) {
            flag = true;
            return;
        } else if (word.length() == 1) {
            if (word.charAt(0) == board[x][y]) {
                flag = true;
            }
            return;
        }
        char c = word.charAt(0);
        if (c == board[x][y]) {
            sign[x][y] = 1;
            String substring = word.substring(1, word.length());
            explore(board, substring, x + 1, y, sign);
            explore(board, substring, x - 1, y, sign);
            explore(board, substring, x, y - 1, sign);
            explore(board, substring, x, y + 1, sign);
        }
        sign[x][y] = 0;
    }

}
