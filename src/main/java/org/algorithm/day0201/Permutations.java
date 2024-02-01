package org.algorithm.day0201;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * @date 2024/2/1 10:29 AM
 */
public class Permutations {

    List<List<Integer>> result = new ArrayList<>();


    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> permute = permutations.permute(nums);
        for (List<Integer> integers : permute) {
            System.out.println(integers);
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> integerList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        permute(integerList, new ArrayList<>());
        return result;
    }

    public void permute(List<Integer> nums, List<Integer> list) {
        if (nums.isEmpty()) {
            result.add(list);
        }
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> rest = new ArrayList<>(list);
            List<Integer> copy = new ArrayList<>(nums);
            rest.add(nums.get(i));
            copy.remove(i);
            permute(copy, rest);
        }
    }


}
