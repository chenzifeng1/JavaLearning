package org.algorithm.day0206;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 * <p>
 * 输入: numRows = 1
 * 输出: [[1]]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= numRows <= 30
 * @date 2024/2/27 7:11 PM
 */
public class PascalsTriangle {


    public static void main(String[] args) {
        PascalsTriangle pascalsTriangle = new PascalsTriangle();
        List<List<Integer>> result = pascalsTriangle.generate(1);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);
        for (int i = 1; i < numRows; i++) {
            int nums = i + 1; //当前行数包含的元素个数
            List<Integer> lastRow = result.get(i-1); // 获取上一行的元素
            List<Integer> rowList = new ArrayList<>(nums);
            // 遍历当前行元素
            for (int j = 0; j < nums; j++) {
                if (j == 0 || j == nums -1) {
                    rowList.add(1);
                }else {
                    rowList.add(lastRow.get(j - 1) + lastRow.get(j));
                }
            }
            result.add(rowList);
        }
        return result;
    }

}
