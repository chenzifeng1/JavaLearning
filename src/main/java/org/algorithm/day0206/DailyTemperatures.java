package org.algorithm.day0206;

import java.util.Stack;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 示例 1:
 * <p>
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * <p>
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 * @date 2024/2/20 4:45 PM
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures.dailyTemperatures0(temperatures);
        for (int i : result) {
            System.out.println(i);
        }
    }

    /**
     * 单调队列
     * 从栈底到栈顶为单调递增的，记录index的值即可
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures0(int[] temperatures) {
        int length = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[length];
        stack.push(0);
        for (int i = 1; i < length; i++) {
            int tem = temperatures[i];
            while (!stack.isEmpty() && tem > temperatures[stack.peek()]) {
                Integer index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }


    /**
     * 从后向前遍历，将结果放到result中，遍历到第i个的时候，
     * 如果T[i+1] > T[i], 则result[i] = 1
     * 否则跳着去判断，看一下T[i+result[i+1]]是否比T[i] 大，因为T[i+1] < T[i], 那么如果T[i+result[i+1]] >T[i] ,
     * 也一定是T[i]的下一个高温点，如果不小于，那么从i+result[i+1]位置开始往后跳result[i+result[i+1]]
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];
        for (int i = length - 2; i >= 0; i--) {
            int j = i + 1;
            if (temperatures[j] > temperatures[i]) {
                result[i] = 1;
            } else {
                boolean flag = false;
                while (j < length - 1 && result[j] != 0) {
                    j = j + result[j];
                    if (temperatures[i] < temperatures[j]) {
                        result[i] = j - i;
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    result[i] = 0;
                }
            }
        }
        return result;
    }


    /**
     * 创建辅助栈，不比peek大的话，就放入辅助栈
     * 下面方法不好，不如直接双重循环
     * <p>
     * fixme time out
     *
     * @return
     */
    @Deprecated
    public int[] dailyTemperatures1(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> assistStack = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            stack.push(temperatures[i]);
        }

        for (int i = 0; i < length; i++) {
            Integer peek = stack.pop();
            int j = i + 1;
            while (!stack.isEmpty() && stack.peek() <= peek) {
                j++;
                assistStack.push(stack.pop());
            }

            result[i] = (j != length) ? j - i : 0;
            while (!assistStack.isEmpty()) {
                stack.push(assistStack.pop());
            }
        }

        return result;
    }
}
