package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [2,1,3]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 * @date 2024/1/24 10:12 AM
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
//        root.left = new TreeNode(1);
        root.right = new TreeNode(Integer.MAX_VALUE);

//        root.left.right = new TreeNode(3);
//        root.right.left = new TreeNode(6);
        ValidateBinarySearchTree validateBinarySearchTree = new ValidateBinarySearchTree();
        boolean result = validateBinarySearchTree.isValidBST(root);
        System.out.println(result);


    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    public boolean checkBST(TreeNode root, Long left, Long right) {
        if (root == null) {
            return true;
        }
        TreeNode lNode = root.left;
        boolean leftResult;
        if (lNode != null) {
            if (lNode.val >= root.val || lNode.val <= left) {
                return false;
            }
            leftResult = checkBST(root.left, left, (long) root.val);
        } else {
            leftResult = true;
        }

        TreeNode rNode = root.right;
        boolean rightResult;
        if (rNode != null) {
            if (rNode.val <= root.val || rNode.val >= right) {
                return false;
            }
            rightResult = checkBST(root.right, (long) root.val, right);
        } else {
            rightResult = true;
        }

        return rightResult && leftResult;
    }

    public boolean validBST(TreeNode root, Long left, Long right) {
        if (root == null) {
            return true;
        }
        if (root.val <= left || root.val >= right) {
            return false;
        }

        return validBST(root.left, left, (long) root.val) && validBST(root.right, (long) root.val, right);
    }


}
