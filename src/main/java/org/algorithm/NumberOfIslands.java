package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * @date 2024/1/29 3:04 PM
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '1', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '1', '1', '1'}
        };
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int result = numberOfIslands.numIslands(grid);
        System.out.println(result);
    }


    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] sign = new int[m][n];
        int num = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (sign[i][j] == 0 && grid[i][j] == '1') {
                    // 碰到 1 且 未探索过的区域记一次
                    num++;
                    explore(grid, sign, i, j, m, n);
                }
            }

        }

        return num;
    }

    public void explore(char[][] grid, int[][] sign, int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        // 来过了，不需要遍历了
        if (sign[i][j] == 1) {
            return;
        } else {
            sign[i][j] = 1;
        }

        if (grid[i][j] == '0') {
            return;
        } else {
            explore(grid, sign, i - 1, j, m, n);
            explore(grid, sign, i + 1, j, m, n);
            explore(grid, sign, i, j - 1, m, n);
            explore(grid, sign, i, j + 1, m, n);
        }
    }
}
