package org.study.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBT {

    /**
     * 深度优先
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 广度优先
     * 优点：没有栈溢出风险，对于层级非常深的树（哪怕是链表形状的树）也能稳健运行。
     * 缺点：代码比递归版本稍长，需要额外的数据结构（队列）。
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); // 取出队首节点

            // 交换左右子节点
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            // 将子节点加入队列，以便后续处理它们的子节点
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        // 构建一个简单的二叉树进行测试:
        //       4
        //      / \
        //     2   7
        //    / \ / \
        //   1  3 6  9
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println("原始二叉树:");
        printTree(root); // 打印原始树

        // 调用翻转函数
        InvertBT solution = new InvertBT();
        TreeNode invertedRoot = solution.invertTree(root); // 可以换成 invertTree1 测试广度优先版本

        System.out.println("\n翻转后的二叉树:");
        printTree(invertedRoot); // 打印翻转后的树
    }

    /**
     * 前序遍历打印二叉树
     */
    public static void printTree(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printTree(root.left);
            printTree(root.right);
        }
    }

}
