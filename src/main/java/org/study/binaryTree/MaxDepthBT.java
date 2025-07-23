package org.study.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepthBT {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); //将树的根节点 root 加入队列。
        int ans = 0;
        // 只要队列queue不为空，就说明还有节点需要访问。
        while (!queue.isEmpty()) {
            //在处理每一层之前，获取当前队列中的节点数量。
            //这个 size 代表了当前层有多少个节点。
            int size = queue.size();
            //它会循环 size 次，每次从队列中取出一个节点进行处理。
            while (size > 0) {
                //从队列的头部取出一个节点（即当前层的下一个节点）。
                // poll() 方法会移除并返回队列的头部元素。
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxDepthBT tree = new MaxDepthBT();

        // 创建二叉树
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // 测试最大深度
        int depth = tree.maxDepth(root);
        System.out.println("二叉树的最大深度为: " + depth); // 预期输出 3
    }

}
