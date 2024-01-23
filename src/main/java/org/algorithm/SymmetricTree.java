package org.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author chenzifeng
 * @version 1.0
 * <p>
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 * @description
 * @date 2024/1/23 12:48 PM
 */
public class SymmetricTree {

    public static void main(String[] args) {

        //测试用例，层次遍历如下 7,-64,-64,-6,-90,-90,-6,88,-44,68,-65,-76,68,-44,88
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(-64);
        root.right = new TreeNode(-64);
        root.left.left = new TreeNode(-6);
        root.left.right = new TreeNode(-90);
        root.right.left = new TreeNode(-90);
        root.right.right = new TreeNode(-6);
        root.left.left.left = new TreeNode(88);
        root.left.left.right= new TreeNode(-44);
        root.left.right.left = new TreeNode(68);
        root.left.right.right = new TreeNode(-65);
        root.right.left.left = new TreeNode(-76);
        root.right.left.right = new TreeNode(68);
        root.right.right.left = new TreeNode(-44);
        root.right.right.right = new TreeNode(88);

        SymmetricTree symmetricTree = new SymmetricTree();
        System.out.println(symmetricTree.isSymmetricIterate(root));


    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }


    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        } else if (right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }


    public boolean isSymmetricIterate(TreeNode root) {
        if (root.left == null) {
            return root.right == null;
        } else if (root.right == null) {
            return false;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root.left);
        deque.addLast(root.right);

        while (deque.size() != 0) {
            int size = deque.size();
            while (size > 0) {
                TreeNode left = deque.pollFirst();
                TreeNode right = deque.pollLast();
                if (left.val != right.val) {
                    return false;
                }
                if (left.left != null && right.right != null) {
                    if (left.left.val == right.right.val) {
                        deque.addFirst(left.left);
                        deque.addLast(right.right);
                    } else {
                        return false;
                    }
                } else {
                    if (left.left != null || right.right != null) {
                        return false;
                    }
                }


                if (left.right!=null && right.left!=null) {
                    if (left.right.val == right.left.val) {
                        deque.addFirst(left.right);
                        deque.addLast(right.left);
                    }else {
                        return false;
                    }
                } else {
                    if (left.right != null || right.left != null) {
                        return false;
                    }
                }
                size -= 2;
            }
        }
        return true;

    }

}
