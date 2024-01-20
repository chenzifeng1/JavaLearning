package org.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 * @date 2024/1/10 10:26 PM
 */
public class SpiralMatrix {

   public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix = {{1,2,3}, { 4,5,6}, {7,8,9}};
//        int[][] matrix = {{7, 8, 9}};
        List<Integer> process = process(matrix);
        System.out.println(process);

    }


    public static List<Integer> process(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int rowStart = 0, rowEnd = n - 1;
        int colStart = 0, colEnd = m - 1;

        List<Integer> result = new ArrayList<>();
        while (rowStart <= rowEnd && colStart <= colEnd) {
            // 第一行 去调最后一个元素
            for (int i = rowStart; i <= rowEnd; i++) {
                result.add(matrix[colStart][i]);
            }
            for (int i = colStart + 1; i <= colEnd; i++) {
                result.add(matrix[i][rowEnd]);
            }
            if (rowStart < rowEnd && colStart < colEnd) {
                for (int i = rowEnd-1; i >= rowStart + 1; i--) {
                    result.add(matrix[colEnd][i]);
                }
                for (int i = colEnd; i >= colStart + 1; i--) {
                    result.add(matrix[i][rowStart]);
                }
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }

        return result;
    }
}
