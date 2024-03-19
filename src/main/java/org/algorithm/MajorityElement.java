package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题
 * @date 2024/3/19 11:46 AM
 */
public class MajorityElement {


    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        int result = new MajorityElement().majorityElement(nums);
        System.out.println(result);
    }

    public int majorityElement(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int now=nums[0];
        int time=1;

        for (int i = 1; i < length; i++) {
            if (now == nums[i]) {
                time++;
            }else {
                time--;
                if (time == 0) {
                    now = nums[i];
                    time = 1;
                }
            }
        }

        return now;
    }


}
