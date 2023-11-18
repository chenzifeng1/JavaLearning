
/**
 * @author chenzifeng
 * @version 1.0
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B 依次拿走纸牌，规定A先拿，B后拿
 * 但是每个玩家每次都只能拿走最左或者最右的纸牌
 * 玩家A，B绝顶聪明，请返回最后获胜者的分数
 * @description
 * @date 2023/10/27 7:04 PM
 */
public class PickSolitaire {


    /**
     * 分析：
     * 1. 玩家只会奔着总体分数最高去拾取卡片
     * 2. 每次都拿最左或者最右的卡牌，有两个原则：1. 获取左右最大的卡牌，2. 获取的卡牌之后不让对方拿更大的卡牌。
     * 3. A先拿，B后拿，这样不用考虑最后卡牌谁拾取，给定卡牌数量之后，即确定了最后一张卡牌的归属
     * 4. 不需要考虑让谁获胜，只需要考虑在给定卡牌情况下，谁能获取到最大值，最后比较一下两者的最大值即可
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] solitaire = new int[]{6, 5, 4, 2, 1, 2, 3, 7, 10};

        int i = method0(solitaire);
        int j = method1(solitaire);
        int k = method3(solitaire);

        System.out.println("i:" + i );
        System.out.println("j:" + j );
        System.out.println("k:" + k );


    }


    public static int method3(int[] arr) {
        int n = arr.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        // 初始化， fmap的对角线就是arr，gmap的对角线全是0
        for (int i = 0; i < n; i++) {
            fmap[i][i] = arr[i];
        }
        for (int i = 1; i < n; i++) {
            // 计算对角线的时候，每次都是从第0行，第i列开始
            int row = 0;
            int col = i;
            // 因为列优先越界，所以只要判断列是否越界即可
            while (col < n) {
                fmap[row][col] = Math.max(arr[row]+gmap[row+1][col], arr[col]+gmap[row][col-1]);
                gmap[row][col] = Math.min(fmap[row+1][col], fmap[row][col-1]);
                row++;
                col++;
            }


        }


        return Math.max(fmap[0][n-1], gmap[0][n-1]);
    }


    /**
     * 傻缓存法
     *
     * @param arr
     * @return
     */
    public static int method1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int length = arr.length;
        // fmap[n][m]  指的是先手在n-m之间能获取的最大分数
        int[][] fmap = new int[length][length];
        // gmap[n][m]  指的是后手在n-m之间能获取的最小分数
        int[][] gmap = new int[length][length];
        // 初始化缓存
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }

        int fTake = firstTake1(arr, 0, length - 1, fmap, gmap);
        int aTake = afterTake1(arr, 0, length - 1, fmap, gmap);

        return Math.max(fTake, aTake);


    }

    public static int firstTake1(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (fmap[l][r] != -1) {
            // 命中缓存，直接返回
            return fmap[l][r];
        }
        int ans = 0;
        if (l == r) {
            ans = arr[l];
        } else {
            int temp1 = arr[l] + afterTake1(arr, l + 1, r, fmap, gmap);
            int temp2 = arr[r] + afterTake1(arr, l, r - 1, fmap, gmap);
            ans = Math.max(temp1, temp2);
        }
        fmap[l][r] = ans;
        return ans;
    }


    public static int afterTake1(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (gmap[l][r] != -1) {
            return gmap[l][r];
        }
        int ans = 0;
        if (l != r) {
            int temp1 = firstTake1(arr, l + 1, r, fmap, gmap);
            int temp2 = firstTake1(arr, l, r - 1, fmap, gmap);
            ans = Math.min(temp1, temp2);
        }
        gmap[l][r] = ans;
        return ans;
    }


    public static int method0(int[] arr) {
        return firstTake0(arr, 0, arr.length - 1);
    }

    /**
     * 先手能拿到的最好的分数
     *
     * @param solitaire
     * @return
     */
    public static int firstTake0(int[] solitaire, int left, int right) {
        if (left == right) {
            // 最后一张牌，先手必拿
            return solitaire[left];
        }
        int lr = solitaire[left] + afterTake0(solitaire, left + 1, right);
        int rr = solitaire[right] + afterTake0(solitaire, left, right - 1);
        return Math.max(lr, rr);
    }


    /**
     * 后手拿牌，后手能拿到的是对手给出的，即能拿到的是两种选择的最小分数
     *
     * @param solitaire
     * @param left
     * @param right
     * @return
     */
    public static int afterTake0(int[] solitaire, int left, int right) {
        if (left == right) {
            // 剩最后一张牌时，后手肯定拿不到牌，因此得分为0
            return 0;
        }
        // 对手拿完之后，我能在当前牌中拿最好的分数
        int lr = firstTake0(solitaire, left + 1, right);
        int rr = firstTake0(solitaire, left, right - 1);
        // 但是由于是对手拿完之后的，能拿到肯定是最坏的结果，此处模拟对手拿到最好的结果
        return Math.min(lr, rr);
    }

}
