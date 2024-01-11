package org.algorithm;

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

    }


    public static int[] process(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int rowStart = 0, rowEnd = n;
        int colStart = 0, colEnd = m;

        int index = 0;


        int[] result = new int[m * n];
        while (rowStart < rowEnd && colStart < colEnd) {
            // 第一行 去调最后一个元素
            for (int i = rowStart; i < rowEnd - 1; i++) {
                result[index++] = matrix[colStart][i];
            }
            for (int i = colStart; i < colEnd - 1; i++) {
                result[index++] = matrix[i][rowEnd];
            }
            for (int i = rowEnd; i > rowStart + 1; i--) {
                result[index++] = matrix[rowEnd][i];
            }
            for (int i = colEnd; i > colStart + 1; i--) {
                result[index++] = matrix[i][rowStart];
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colStart--;
        }
        while (rowStart < rowEnd) {
            result[index++] = matrix[rowStart++][colStart];
        }
        while (colStart < colEnd) {
            result[index++] = matrix[rowStart][colStart++];
        }

        return result;
    }
}
