package org.algorithm;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class InvertBinaryTree {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        head.left = node2;
        head.right = node3;
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.left = node4;
        node2.right = node5;
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node3.left = node6;
        node3.right = node7;
        TreeNode treeNode = invertTree(head);

        List<Integer> integers = BinaryTreeInorderTraversal.inorderTraversalIterate(treeNode);
        TreeNode treeNode1 = invertTreeIterate(head);
        List<Integer> integers1 = BinaryTreeInorderTraversal.inorderTraversalIterate(treeNode1);
        System.out.println(integers);
        System.out.println(integers1);

    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.right);
        invertTree(root.left);
        return root;
    }

    public static TreeNode invertTreeIterate(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right !=null) {
                queue.offer(node.right);
            }
        }
        return root;
    }


}
