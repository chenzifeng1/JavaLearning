package org.algorithm.day0205;


/**
 * @author chenzifeng
 * @version 1.0
 * @description 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 * @date 2024/2/5 10:22 AM
 */
public class SearchInRotatedSortedArray {

    int result = -1;

    public static void main(String[] args) {
        SearchInRotatedSortedArray searchInRotatedSortedArray = new SearchInRotatedSortedArray();
        int[] nums = {3, 1};
        int target = 1;
        int search = searchInRotatedSortedArray.search1(nums, target);
        System.out.println(search);
    }

    /**
     * 迭代实现：每次判断一下当前区间是否是单调递增的，如果是，则直接二分查找，否则，将区间分为左右两部分，分别判断
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[0] <= nums[mid]) {
                // 单调递增
                if (nums[0] <= target && target < nums[mid]) {
                    // 排除 在0-mid的区间的话，剩下的就是left+1的操作
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }


    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        } else if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        search(nums, target, 0, nums.length - 1);
        return result;
    }

    public void search(int[] nums, int target, int left, int right) {
        if (left >= right) {
            result = target == nums[left] ? left : result;
            return;
        }
        int mid = left + ((right - left) >> 1);
        if (nums[mid] == target) {
            result = mid;
            return;
        }
        if (nums[left] > nums[right]) {
            // 两边都有可能找到target
            search(nums, target, left, mid - 1);
            search(nums, target, mid + 1, right);
        } else {
            // 单调递增，直接二分
            if (target < nums[mid]) {
                search(nums, target, left, mid - 1);
            } else {
                search(nums, target, mid + 1, right);
            }
        }
    }


}
