package org.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 * @date 2024/1/25 11:16 AM
 */
public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        new FlattenBinaryTreeToLinkedList().flattenO1(root);
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }


    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        fatten(root, list);
        TreeNode temp = root;
        for (int i = 1; i < list.size(); i++) {
            temp.right = list.get(i);
            temp.left = null;
            temp = temp.right;
        }
    }

    public void fatten(TreeNode root, final List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        fatten(root.left, list);
        fatten(root.right, list);
    }


    /**
     * flatten sub tree to linked list
     *
     * @param root
     */
    public TreeNode flattenO1(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp = root.right;
        root.right = flattenO1(root.left);
        TreeNode index = root;
        while (index.right != null) {
            index = index.right;
        }
        index.right = flattenO1(temp);
        root.left = null;
        return root;
    }


}
