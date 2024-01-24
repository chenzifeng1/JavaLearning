package org.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 * <p>
 * <p>
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 * @date 2024/1/24 6:01 PM
 */
public class KthSmallestElementInABst {

    private int result;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        KthSmallestElementInABst kthSmallestElementInABst = new KthSmallestElementInABst();
        System.out.println(kthSmallestElementInABst.kthSmallest(root, 3));

    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        return searchKSmallest(root, k);
    }

    /**
     * 左子树查询
     *
     * @param root
     * @param k
     * @return
     */
    public int searchKSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            if (result.size() == k) {
                return root.val;
            }
            root = root.right;

        }

        return -1;
    }


}
