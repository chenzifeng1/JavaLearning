package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个正整数 num，将这个数字拆分成多个不相等的正整数，并使这些整数的和等于 num。
 * 切分割方法中，满足后面的数不小于前面的数。
 * 例如：输入 num = 12，输出 [1, 2, 3] 或者 [4, 6]。
 * 输入 num = 10，输出 [1, 9] 或者 [1, 2, 8]。
 * @date 2023/12/22 12:44 PM
 */
public class SplitNum {

    public static void main(String[] args) {
        int num = 17;

        int process = process(1, num);
        int process1 = process1(num);
        int process2 = process2(num);

        System.out.println(process);
        System.out.println(process1);
        System.out.println(process2);
    }


    /**
     * 对数字num来说可以获取到到最大到拆分情况
     * 如果从（0，num）开始算，那么会进行无限递归，因为在循环的第一步是从 i=0开始，重复调用process(0,num)
     * 因此，构造一个预制数1，num+1， 表示从上一个数 切的是1，现在剩余num+1，那么在第一次递归中，就会自然而然的进入到（1，num）的情况，
     * 而且不会遗漏
     *
     * @return，
     */
    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        } else if (pre == rest) {
            // 如果上一个拆分出来的数据 大于 剩余数据的一半，说明后面数据怎么拆都满足不了，直接返回
            return 1;
        }

        // 说明rest是可以继续拆分,那么下一个需要拆分都数字至少是从pre开始，到rest/2结束
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }


    public static int process1(int num) {
        // dp[i][j] i 表示上一个拆出来到数字，j表示还需要拆到值，其中 i>j时 dp[i][j]无效
        int[][] dp = new int[num + 1][num + 1];
        for (int i = 0; i <= num; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int i = num - 1; i >= 1; i--) {
            for (int j = i + 1; j <= num; j++) {
                int temp = 0;
                for (int k = i; k <= j; k++) {
                    temp += dp[k][j - k];
                }
                dp[i][j] = temp;
            }
        }

        return dp[1][num];
    }

    public static int process2(int num) {
        // dp[i][j] i 表示上一个拆出来到数字，j表示还需要拆到值，其中 i>j时 dp[i][j]无效
        int[][] dp = new int[num + 1][num + 1];
        for (int i = 0; i <= num; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }
        for (int i = num - 1; i >= 1; i--) {
            for (int j = i + 1; j <= num; j++) {
                dp[i][j] = dp[i+1][j] + dp[i][j-i];
            }
        }
        return dp[1][num];
    }


}
