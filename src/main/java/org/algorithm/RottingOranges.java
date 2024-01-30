package org.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * <p>
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 * 示例 3：
 * <p>
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 * @date 2024/1/30 2:20 PM
 */
public class RottingOranges {

    private int result = 0;


    public static void main(String[] args) {
        RottingOranges rottingOranges = new RottingOranges();
//        int[][] grid = { {2, 1, 1, 0},
//                         {1, 1, 0, 1},
//                         {0, 1, 1, 2}};
        int[][] grid = {{2, 1, 1},
                        {1, 1, 0},
                        {0, 1, 1}};
        int result = rottingOranges.orangesRotting1(grid);
        System.out.println(result);

    }


    public int orangesRotting1(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean flag = false;


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            return 0;
        }

        int time = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int x = point[0];
                int y = point[1];
                grid[x][y] = 0;
                add(grid, x - 1, y, m, n, queue);
                add(grid, x + 1, y, m, n, queue);
                add(grid, x, y - 1, m, n, queue);
                add(grid, x, y + 1, m, n, queue);
            }
            time++;
        }


        flag = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    flag = true;
                }
            }
        }
        return flag ? -1 : time;
    }

    void add(int[][] grid, int x, int y, int m, int n, Queue<int[]> queue) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return;
        }
        if (grid[x][y] == 0 || grid[x][y] == 2) {
            return;
        }
        // 污染
        grid[x][y] = 0;
        queue.offer(new int[]{x, y});
    }


}
