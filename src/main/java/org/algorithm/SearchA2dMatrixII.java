package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 * @description
 * @date 2024/1/12 4:19 PM
 */
public class SearchA2dMatrixII {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int k = 20;

        boolean b = searchMatrix1(matrix, k);
        System.out.println(b);

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] <= target) {
                for (int j = 0; j < n; j++) {
                    if (target == matrix[i][j]) {
                        return true;
                    } else if (target < matrix[i][j]) {
                        break;
                    }
                }
            }
            if (matrix[i][0] > target) {
                return false;
            }
        }
        return false;
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            int left = 0;
            int right = n-1;
            int mid = left + ((right - left) >> 1);
            if (target > matrix[i][0]) {
                while (left <= right) {
                    if (target > matrix[i][mid]) {
                        left = mid +1;
                        mid = left + ((right - left) >> 1);
                    }else if (target < matrix[i][mid]){
                        right = mid-1;
                        mid = left + ((right - left) >> 1);
                    }else {
                        return true;
                    }
                }
            }else if (target == matrix[i][0]) {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

}
