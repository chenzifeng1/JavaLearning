package org.algorithm.day0204;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 * @date 2024/2/4 3:21 PM
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6, 10};
        int target = 10;
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        int searchInsert = searchInsertPosition.searchInsert1(nums, target);
        System.out.println(searchInsert);

    }

    public int searchInsert(int[] nums, int target) {
        int length = nums.length;

        if (target > nums[length - 1]) {
            return length;
        } else if (target < nums[0]) {
            return 0;
        }
        return searchInsert(nums, 0, length - 1, target);
    }

    public int searchInsert1(int[] nums, int target) {
        int length = nums.length;
        int left = 0, right = length - 1;

        if (target > nums[right]) {
            return length;
        } else if (target < nums[0]) {
            return 0;
        }

        int mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return target > nums[left] ? left + 1 : left;
    }


    public int searchInsert(int[] nums, int left, int right, int target) {
        if (left >= right) {
            return nums[left] < target ? left + 1 : left;
        }
        int mid = left + ((right - left) >> 1);
        if (target == nums[mid]) {
            return mid;
        } else if (target < nums[mid]) {
            return searchInsert(nums, left, mid - 1, target);
        } else {
            return searchInsert(nums, mid + 1, right, target);
        }
    }
}
