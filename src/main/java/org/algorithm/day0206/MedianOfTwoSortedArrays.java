package org.algorithm.day0206;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * @date 2024/2/7 2:29 PM
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2,4};
        double medianSortedArrays = medianOfTwoSortedArrays.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    /**
     * 数组有序，取中位数是变相的获取 topK，其中k=(m+n)/2 (看奇偶数)
     * 那么在数组有序的情况下，我们通过mid每次可以排除一半的元素，直到排除的元素数量=k
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        // 如果是奇数，那么left == right，如果是偶数，那么left +1 == right
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left)
                + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    public int getKth(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int k) {
        int len1 = r1 - l1 + 1;
        int len2 = r2 - l2 + 1;
        // base case 1: 始终让一个小数组在第一位，这样后面只需要判断小数组是否为0即可
        if (len1 > len2) {
            return getKth(nums2, l2, r2, nums1, l1, r1, k);
        }
        // base case 2: 小数组已经到头了
        if (len1 == 0) {
            return nums2[l2 + k - 1];
        }
        // base case 3: 两者数量相当，但是k就剩一个了




        if (k == 1) {
            return Math.min(nums1[l1], nums2[l2]);
        }
        int start1 = l1 + Math.min(k / 2, len1) - 1;
        int start2 = l2 + Math.min(k / 2, len2) - 1;


        //other case : 剩余的数量都可以减掉k/2个元素
        if (nums1[start1] < nums2[start2]) {
            // 如果第一个数组的l+k小于第二个数组的l+k 那么可以将第一个数组的前l+k个数排除掉，因为前l+k个数在topk-1里面
            return getKth(nums1, start1+1, r1, nums2, l2, r2, k - (start1 - l1 + 1));
        } else {
            return getKth(nums1, l1, r1, nums2, start2+1, r2, k - (start2 - l2 + 1));
        }

    }


}
