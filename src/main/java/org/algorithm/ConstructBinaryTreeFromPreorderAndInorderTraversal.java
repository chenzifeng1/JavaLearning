package org.algorithm;


import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 * <p>
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 * @date 2024/1/25 2:06 PM
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {

        //preorder = [3,9,4,20,15,7], inorder = [9,4,3,15,20,7]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int[] preorder = {3, 9, 4, 20, 15, 7};
        int[] inorder = {9, 4, 3, 15, 20, 7};

        TreeNode head = new ConstructBinaryTreeFromPreorderAndInorderTraversal()
                .buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);

        List<Integer> integers = BinaryTreeInorderTraversal.inorderTraversalIterate(head);
        System.out.println(integers);

    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int pl, int pr, int il, int ir) {
        if (pl > pr || il > ir) {
            return null;
        }
        if (pl == pr && il == ir) {
            return new TreeNode(preorder[pl]);
        }
        int headVal = preorder[pl];
        TreeNode head = new TreeNode(headVal);
        int i;
        for (i = il; i <= ir; i++) {
            if (inorder[i] == headVal) {
                break;
            }
        }
        head.left = buildTree(preorder, inorder, pl + 1, pl + (i - il), il,  i - 1);
        head.right = buildTree(preorder, inorder, pl + (i - il) + 1, pr, i + 1, ir);
        return head;
    }

}
