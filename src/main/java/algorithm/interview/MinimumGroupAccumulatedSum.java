package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个正数数组arr,
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 * @date 2023/12/25 6:54 PM
 */
public class MinimumGroupAccumulatedSum {

    public static void main(String[] args) {
        int[] arr = new int[]{12, 31, 5, 1, 9, 13, 21, 8};


        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        System.out.println("sum:" + sum);
        int process = process(arr, 0, sum >> 1);
        System.out.println(process);


    }

    /**
     * 返回 从index 到 length 这段范围内, 尽可能接近但是有不超过rest的累加和
     *
     * @param arr
     * @param index
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 0;
        }
        if (rest < 0) {
            // 不合法的情况
            return 0;
        }

        // 拿这个值
        int p1 = 0;
        if (arr[index] <= rest) {
            p1 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }


        // 不拿这个值
        int p2 = process(arr, index + 1, rest);
        return Math.max(p1, p2);
    }


}
