package org.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description https://leetcode.cn/problems/n-queens/
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[["Q"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 * @date 2023/12/27 11:12 PM
 */
public class NQueue {


    public static void main(String[] args) {
        int n = 7;
        int i = call1(n);
        System.out.println(i);
    }


    public static List<Location> call(int n) {
        int[] nums = new int[n];
        List<Location> result = new ArrayList<>();
        List<List<String>> r = new ArrayList<>();
        process(n, nums, 0, result);
        for (Location location : result) {
            List<String> strings = generateStr(location.getLocaction(), n);
            r.add(strings);
        }


        int size = result.size();
        if (size != 0) {
            System.out.println("result size:" + size);
            for (Location location : result) {
                System.out.println(location.toString());
            }
        }
        return result;
    }

    /**
     * 递归版本：处理 index 行皇后摆放位置
     *
     * @param n
     * @param location 使用一个一维数组记录每一行皇后的列位置
     * @param index
     * @return
     */
    public static void process(int n, int[] location, int index, final List<Location> result) {
        int[] copy = copy(n, location);
        if (index == n) {
            // 越界，说明摆放成功了
            Location success = new Location(copy);
            result.add(success);
            return;
        }
        // 判断是否可以摆放成功
        for (int i = 0; i < n; i++) {
            boolean isOk = true;
            for (int j = 0; j < index; j++) {
                // 第j个皇后的列位置
                int queueLocation = copy[j];
                if (i == queueLocation
                        || Math.abs(index - j) == Math.abs(i - queueLocation)) {
                    // 当前位置和第j个皇后共列或者共斜线
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                // 当前位置和之前的皇后没冲突, 将当前位置index的位置继续做尝试
                copy[index] = i;
                process(n, copy, index + 1, result);
            }
        }
    }


    public static int call1(int n) {
        int limit = (n >= 32) ? 0 : (1 << n) - 1;
        return process1(limit, 0, 0, 0);
    }

    /**
     * @param limit
     * @param col
     * @param leftDiagonal
     * @param rightDiagonal
     * @return
     */
    public static int process1(int limit, int col, int leftDiagonal, int rightDiagonal) {
        if (limit == col) {
            // 当所有的列都无法选择的时候，说明所有的皇后都已经找到位置

            return 1;
        }
        // 这里求出来不为1的地方，是可以放皇后的位置
        int pos = limit & (~(col | leftDiagonal | rightDiagonal));

        // 用来存储最右侧的1
        int mostRightOne = 0;
        int res = 0;
        // 所有pos位置都尝试
        while (pos != 0) {
            // 获取最右侧的1，来放置皇后
            mostRightOne = pos & (~pos + 1);
            // 尝试当前位置
            pos = pos - mostRightOne;
            res += process1(limit,
                    col | mostRightOne,
                    (leftDiagonal | mostRightOne) << 1,
                    (rightDiagonal | mostRightOne) >>> 1);
        }
        return res;
    }


    public static List<String> generateStr(int[] location, int n) {
        List<String> result = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int q = location[i];
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                stringBuilder.append(j == q ? "Q" : ".");
            }
            result.add(stringBuilder.toString());
        }

        return result;
    }


    public static class Location {
        int[] locaction;

        public Location(int[] locaction) {
            this.locaction = locaction;
        }

        public int[] getLocaction() {
            return locaction;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (int i = 0; i < locaction.length; i++) {
                stringBuilder.append("[")
                        .append(i).append(",")
                        .append(locaction[i]).append("]")
                        .append(" ");
            }
            stringBuilder.append("]");

            return "Location{" +
                    stringBuilder.toString() +
                    '}';
        }
    }


    public static int[] copy(int n, int[] location) {
        int[] newLocation = new int[n];
        for (int i = 0; i < location.length; i++) {
            newLocation[i] = location[i];
        }
        return newLocation;
    }
}
