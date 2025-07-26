package org.study.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称
 */
public class SymmetricTree {

    /**
     * 错误用法：
     * 尝试用两种不同的中序遍历来解决问题。
     * 方法的根本问题在于，它只比较了节点的值，而忽略了树的结构。
     * 方法完全跳过了null节点，导致结构信息丢失。
     * 不通过例子：[1,2,2,2,null,2]
     */
    public boolean isSymmetricF(TreeNode root) {
        List<Integer> listA=new ArrayList<>();
        List<Integer> listB=new ArrayList<>();
        inOrder1(root,listA);inOrder2(root,listB);
        return listA.equals(listB);
    }

    void inOrder1(TreeNode root,List<Integer> list){
        if(root==null)return;
        inOrder1(root.left,list);
        list.add(root.val);
        inOrder1(root.right,list);
    }
    void inOrder2(TreeNode root,List<Integer> list){
        if(root==null)return;
        inOrder2(root.right,list);
        list.add(root.val);
        inOrder2(root.left,list);
    }

    /**
     * 方法1：递归
     * 如何判断两个树（T1 和 T2）是否互为镜像？
     * 它们的根节点值必须相等。
     * 1. T1 的左子树必须和 T2 的右子树互为镜像。
     * 2. T1 的右子树必须和 T2 的左子树互为镜像。
     */
    public boolean isSymmetric1(TreeNode root){
        return check(root.left, root.right);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        // 当node1和node2都不等于null的时候，进入这条递归return。
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * 方法2：迭代
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // 初始时，将需要比较的两个节点（根的左右孩子）入队
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            // 每次从队列中取出两个节点进行比较
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            // 如果两个节点都为 null，说明这对是镜像的，继续下一对
            if (node1 == null && node2 == null) {
                continue;
            }

            // 如果一个为 null，或值不相等，则不对称
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            // 关键：按镜像顺序将子节点入队
            // 比较 node1 的左孩子和 node2 的右孩子
            queue.offer(node1.left);
            queue.offer(node2.right);

            // 比较 node1 的右孩子和 node2 的左孩子
            queue.offer(node1.right);
            queue.offer(node2.left);
        }

        // 如果队列处理完毕都没有返回 false，说明树是对称的
        return true;
    }

    public static void main(String[] args) {
    // 构造测试用例 [1,2,2,3,4,4,3]
    // 二叉树结构示例:
    //       1
    //      / \
    //     2   2
    //    / \ / \
    //   3  4 4  3
    // 该二叉树是轴对称的
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(3);

    SymmetricTree solution = new SymmetricTree();
    boolean result1 = solution.isSymmetric1(root);
    boolean result2 = solution.isSymmetric2(root);
    boolean result3 = solution.isSymmetricF(root);

    System.out.println("递归方法结果: " + result1); // 应该输出 true
    System.out.println("迭代方法结果: " + result2); // 应该输出 true
    System.out.println("错误方法结果: " + result3); // 可能输出错误结果
    }
}
