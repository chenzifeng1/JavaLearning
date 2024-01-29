package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 * @date 2024/1/29 11:28 AM
 */
public class BinaryTreeMaximumPathSum {

    int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTreeMaximumPathSum binaryTreeMaximumPathSum = new BinaryTreeMaximumPathSum();
        binaryTreeMaximumPathSum.searchMaxPathSum(root);
        System.out.println(binaryTreeMaximumPathSum.ans);

    }

    public int searchMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树中的最大路径和
        int p1 = searchMaxPathSum(root.left);
        // 右子树中的最大路径和
        int p2 = searchMaxPathSum(root.right);

        int tempAns = root.val + p1 + p2;
        if (ans < tempAns) {
            ans = tempAns;
        }
        // 如果该节点采用，那么就是该节点的值 + 左右子树中最长的一条
        int subMax = Math.max(p1, p2);
        if (root.val > 0) {
            // 大于0则可以收纳
            return root.val + Math.max(subMax, 0);
        } else {
            return Math.max(subMax + root.val, 0);
        }
    }

}
