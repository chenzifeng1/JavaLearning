package org.algorithm.day0205;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * @date 2024/2/4 10:24 PM
 */
public class FindFirstAndLastPositionOfElementInSortedArray {


    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray f = new FindFirstAndLastPositionOfElementInSortedArray();
        int[] nums = {5, 7, 7, 8, 8, 8, 8, 8, 8, 8, 10};
        int[] result = f.searchRange(nums, 8);
        System.out.println(result[0] + " " + result[1]);
    }
    


    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] result = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        search(nums, result, 0, nums.length - 1, target);

        return result[0] == Integer.MAX_VALUE ? new int[]{-1, -1} : result;
    }

    public void search(int[] nums, int[] result, int left, int right, int target) {
        if (left > right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        if (nums[mid] == target) {
            result[0] = Math.min(result[0], mid);
            result[1] = Math.max(result[1], mid);
            search(nums, result, left, mid - 1, target);
            search(nums, result, mid + 1, right, target);
        } else if (nums[mid] < target) {
            search(nums, result, mid + 1, right, target);
        } else {
            search(nums, result, left, mid - 1, target);
        }
    }

}
