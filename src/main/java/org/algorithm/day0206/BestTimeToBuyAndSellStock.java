package org.algorithm.day0206;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 * @date 2024/2/26 6:31 PM
 */
public class BestTimeToBuyAndSellStock {

    int result = 0;

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(bestTimeToBuyAndSellStock.maxProfit(prices));
        bestTimeToBuyAndSellStock.doProcess(prices, 0);
        System.out.println(bestTimeToBuyAndSellStock.result);
        System.out.println(bestTimeToBuyAndSellStock.doProcess1(prices, 1, prices[0]));
        System.out.println(bestTimeToBuyAndSellStock.maxProfit1(prices));

    }


    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[] max = new int[length];
        int maxT = Integer.MIN_VALUE;
        for (int i = length - 1; i >= 0; i--) {
            max[i] = Math.max(maxT, prices[i]);
            maxT = max[i];
        }
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (result < max[i] - prices[i]) {
                result = max[i] - prices[i];
            }
        }
        return result;
    }

    /**
     * 返回从i到length中到最大值
     *
     * @param price
     * @param i
     * @return
     */
    private int doProcess(int[] price, int i) {
        if (i == price.length) {
            return 0;
        }
        int restMax = doProcess(price, i + 1);
        // 未来最大值 - 当前值 = 当前买股票可以得到的最好收益
        if (result < restMax - price[i]) {
            result = restMax - price[i];
        }
        return Math.max(price[i], restMax);

    }


    /**
     * 改为从 i 到 length -1 位置上能获取到的最大的利益
     *
     * @param prices
     * @param i
     * @return
     */
    private int doProcess1(int[] prices, int i, int before) {
        if (i == prices.length) {
            return 0;
        }
        // 继续持有
        int p1 = doProcess1(prices, i + 1, before);
        // 重新计算
        int p2 = doProcess1(prices, i + 1, prices[i]);
        // 结算
        int p3 = 0;
        if (prices[i] - before > 0) {
            p3 = prices[i] - before;
        }
        return Math.max(Math.max(p1, p2), p3);
    }


    public int maxProfit1(int[] prices) {
        int length = prices.length;
        int max = Integer.MIN_VALUE;
        for (int price : prices) {
            max = Math.max(max, price);
        }
        int[][] dp = new int[length + 1][max + 1];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= max; j++) {
                int p1 = dp[i + 1][j];
                int p2 = dp[i + 1][prices[i]];
                int p3 = Math.max(prices[i] - j, 0);
                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }
        return dp[1][prices[0]];
    }

}
