package org.algorithm.day0201;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * @date 2024/2/1 10:31 AM
 */
public class Subsets {
    List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = {1, 2, 3, 4, 5};
        List<List<Integer>> subsets1 = subsets.subsets1(nums);
        subsets1.forEach(System.out::println);
    }

    public List<List<Integer>> subsets(int[] nums) {
        result.add(new ArrayList<>());
        if (nums == null || nums.length == 0) {
            return result;
        }
        add(nums, 0, new ArrayList<>());
        return result;
    }

    public void add(int[] nums, int index, List<Integer> list) {
        if (index == nums.length) {
            return;
        }
        List<Integer> newList = new ArrayList<>(list);

        // 采用这个
        newList.add(nums[index]);
        result.add(newList);
        add(nums, index + 1, newList);
        // 不采用这个
        add(nums, index + 1, list);
    }


    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        int time = (1 << length) ;
        for (int i = 0; i < time; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                int index = 1 << j;
                if ((i & index) != 0) {
                    list.add(nums[j]);
                }
            }
            result.add(list);
        }
        return result;
    }


}
