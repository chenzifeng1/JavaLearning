package org.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);3
        head.left = node2;
//        head.right = node3;
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.left = node4;
        node2.right = node5;
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
//        node3.left = node6;
//        node3.right = node7;

        List<Integer> integers = inorderTraversalIterate(head);
        System.out.println(integers);


    }

    /**
     * 中序便利，左根右 迭代版本
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversalIterate(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty() || root !=null) {
            while (root!= null) {
                stack.add(root.left);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            result.add(pop.val);
            root = pop.right;
        }
        return result;
    }


    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }


}
