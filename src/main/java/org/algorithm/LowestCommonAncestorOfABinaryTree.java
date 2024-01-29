package org.algorithm;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 * <p>
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 * @date 2024/1/25 9:10 PM
 */
public class LowestCommonAncestorOfABinaryTree {
    TreeNode ans;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(8);
        TreeNode node9 = new TreeNode(7);
        TreeNode node10 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        node4.left = node9;
        node4.right = node10;
        LowestCommonAncestorOfABinaryTree lowestCommonAncestorOfABinaryTree = new LowestCommonAncestorOfABinaryTree();
        lowestCommonAncestorOfABinaryTree.searchLowestCommonAncestor(root, node1, node2);
        System.out.println(lowestCommonAncestorOfABinaryTree.ans.val);

    }


    public TreeNode searchLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (ans != null) {
            return null;
        }
        if (root == null) {
            return root;
        }
        boolean isPget = false;
        boolean isQget = false;
        if (root == p) {
            isPget = true;
        } else if (root == q) {
            isQget = true;
        }
        // 继续往下走
        TreeNode ancestor1 = searchLowestCommonAncestor(root.left, p, q);
        TreeNode ancestor2 = searchLowestCommonAncestor(root.right, p, q);
        if (ancestor1 == p || ancestor2 == p) {
            isPget = true;
        }
        if (ancestor1 == q || ancestor2 == q) {
            isQget = true;
        }
        if (isQget && isPget) {
            ans = root;
            return root;
        }
        if (isQget) {
            return q;
        }

        if (isPget) {
            return p;
        }

        return null;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果是 root为最近公共祖先的情况，那么递归上层必有 另一个子树为null的情况，
        // 那么这种情况下只需要让root 指向不为null的 递归结果即可
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

}
