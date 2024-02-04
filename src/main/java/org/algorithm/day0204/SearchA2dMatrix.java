package org.algorithm.day0204;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个满足下述两条属性的 m x n 整数矩阵：
 * <p>
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 * @date 2024/2/4 3:46 PM
 */
public class SearchA2dMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}};
        SearchA2dMatrix searchA2dMatrix = new SearchA2dMatrix();
        boolean result = searchA2dMatrix.searchMatrix(matrix, 12);
        System.out.println(result);
    }

    /**
     * 列头二分定位，行内二分定位
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowStart = 0, rowEnd = matrix.length - 1;
        int colStart = 0, colEnd = matrix[0].length - 1;

        while (rowStart < rowEnd) {
            int mid = rowStart + ((rowEnd - rowStart) >> 1);
            if (target == matrix[mid][0]) {
                return true;
            } else if (target < matrix[mid][0]) {
                rowEnd = mid - 1;
            } else {
                rowStart = mid + 1;
            }
        }
        while (rowStart >= 0 && matrix[rowStart][0] > target) {
            rowStart--;
        }
        if (rowStart < 0) {
            return false;
        }
        while (colStart < colEnd) {
            int mid = colStart + ((colEnd - colStart) >> 1);
            if (target == matrix[rowStart][mid]) {
                return true;
            } else if (target < matrix[rowStart][mid]) {
                colEnd = mid - 1;
            } else {
                colStart = mid + 1;
            }
        }

        return matrix[rowStart][colStart] == target;

    }
}
