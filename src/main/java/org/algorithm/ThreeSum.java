package org.algorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 * <p>
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * @date 2024/3/12 1:06 PM
 */
public class ThreeSum {


    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> collect = Arrays.stream(nums)
                .boxed() // 将int转换为Integer
                .collect(Collectors.toList());
        // nums 降序排列
        collect.sort(new CustomComparator());

        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length - 3; i++) {
            // 探测以nums[i]为起始的数组
            int min = collect.get(i);
            int start = i + 1, end = length-1;
            while (start < end) {
                int sum = min + collect.get(start) + collect.get(end);
                if (sum == 0) {
                    List<Integer> list = Arrays.asList(min, collect.get(start), collect.get(end));
                    result.add(list);
                    break;
                }else if (sum >0) {
                    end--;
                }else {
                    start++;
                }
            }
        }
        return result;
    }

    public static class CustomComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }


}
