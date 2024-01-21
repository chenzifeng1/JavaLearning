package org.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {

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
        int i = maxDepth(head);
        int j = maxDepthByIterate(head);
        System.out.println(i);
        System.out.println(j);

    }

    public static int maxDepth(TreeNode root) {
        if (root==null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.right), maxDepth(root.left));
    }

    /**
     * 广度有限便利，将每层节点入队，然后依次取出去判断是否有下一层
     * @param root
     * @return
     */
    public static int maxDepthByIterate(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();

            while (sz >0) {
                TreeNode poll = queue.poll();
                if (poll.left!=null) {
                    queue.add(poll.left);
                }
                if (poll.right!=null) {
                    queue.add(poll.right);
                }
                sz--;
            }
            ans+=1;
        }

        return ans;
    }


}
