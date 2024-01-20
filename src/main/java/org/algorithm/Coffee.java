package org.algorithm;

import java.util.*;

/**
 * @author chenzifeng
 * @version 1.0
 * 给定一个数组arr,arr代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N,表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台洗咖啡的机器，一次只能洗一个杯子，时间耗费a,洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b,咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[]arr、intN,inta、intb
 * <p>
 * 思路：
 * 1. 咖啡机轮流泡咖啡，耗时arr[i]
 * 2. 只有一个洗杯子的机器，耗时a
 * @description
 * @date 2023/12/7 8:55 PM
 */
public class Coffee {

    public static void main(String[] args) {
        int[] arr = {5, 8, 2};
        int n = 3;
        int a = 1, b = 2;

        if (arr.length == 0) {
            System.out.println(0);
        }
        // 获取所有人最短排队时间
        int[] drinks = lineUp(arr, n);

        Integer process1 = process1(drinks, a, b, 0, 0);
        Integer process2 = process2(drinks, a, b);

        System.out.println(process1);
        System.out.println(process2);

    }


    /**
     * 记录所有人喝完的最短时间
     *
     * @param arr
     * @param n
     * @return
     */
    public static int[] lineUp(int[] arr, int n) {
        // 使用小顶堆 模拟所有人最快喝完咖啡的时间
        PriorityQueue<CoffeeMachine> queue = new PriorityQueue<>(new MachineComparetor());
        for (int i : arr) {
            queue.add(new CoffeeMachine(0, i));
        }
        // 记录所有人喝咖啡的时间点
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            CoffeeMachine poll = queue.poll();
            drinks[i] = poll.getBeginTime() + poll.getUseTime();
            poll.setBeginTime(drinks[i]);
            queue.add(poll);
        }

        return drinks;
    }


    /**
     * @param drinks
     * @param a
     * @param b
     * @param index    该处理第几号人的咖啡杯了
     * @param washLine 洗咖啡杯机器的下次可用时间
     *                 第i号人的杯子处理干净的最短时间
     * @return
     */
    private static Integer process1(int[] drinks, int a, int b, int index, int washLine) {
        if (index >= drinks.length) {
            return 0;
        }
        int drink = drinks[index];
        // 选择洗杯子，那么自身干净的时间就是：自己喝咖啡结束的时间或者洗杯机可用时间中的最大值 + 洗杯子所用的时间
        int selfClean1 = Math.max(drink, washLine) + a;
        // 那么该情况下就是能得到的最好时间 就是 以selfClean1为洗杯机空闲时间去考虑下一杯的选择情况
        Integer p1 = Math.max(selfClean1, process1(drinks, a, b, index + 1, selfClean1));
        // 选择自然风干，那么自身干净时间就是 drink + b 但是洗杯机可用时间依然是 washLine，两者取最大
        Integer p2 = Math.max(drink + b, process1(drinks, a, b, index + 1, washLine));
        return Math.max(p1, p2);
    }


    private static Integer process2(int[] drinks, int a, int b) {
        int n = drinks.length;

        int maxFree = 0;
        for (int i = 0; i < n; i++) {
            maxFree = Math.max(drinks[i], maxFree) + a;
        }
        // dp[i][j] 表示 第i号杯子在第j分钟能洗完杯子的最好情况
        int[][] dp = new int[n + 1][maxFree + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= maxFree; j++) {
                int drink = drinks[i];
                int selfClean1 = Math.max(drink, j) + a;
                if (selfClean1 > maxFree) {
                    continue;
                }
                Integer p1 = Math.max(selfClean1, dp[i + 1][selfClean1]);
                Integer p2 = Math.max(drink + b, dp[i + 1][j]);
                dp[i][j] = Math.max(p1, p2);
            }
        }

        return dp[0][0];
    }


    public static class MachineComparetor implements Comparator<CoffeeMachine> {
        @Override
        public int compare(CoffeeMachine o1, CoffeeMachine o2) {
            return (o1.getBeginTime() + o1.getUseTime()) - (o2.getBeginTime() + o2.getUseTime());
        }
    }


    public static class CoffeeMachine {
        int beginTime;

        int useTime;

        public CoffeeMachine(int beginTime, int useTime) {
            this.beginTime = beginTime;
            this.useTime = useTime;
        }

        public CoffeeMachine() {
        }

        public int getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(int beginTime) {
            this.beginTime = beginTime;
        }

        public int getUseTime() {
            return useTime;
        }

        public void setUseTime(int useTime) {
            this.useTime = useTime;
        }

    }

}
