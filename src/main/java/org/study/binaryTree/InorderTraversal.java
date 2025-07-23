package org.study.binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {

    /**
     * 二叉树的中序遍历
     * 递归
     * @param root 根节点
     * @return List集合
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    public void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        // 处理当前节点。 当左子树（或左侧所有子孙节点）都被访问完毕后，才将当前节点 root 的值 (root.val) 添加到 result 列表中。
        result.add(root.val);
        inorder(root.right, result);
    }

    /**
     * 迭代法
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stk = new LinkedList<>();

        //root 为 null (表示当前没有节点可以继续向左探索)。
        //stk 为空 (表示栈中没有待处理的节点了)。只要还有节点可以探索（root != null）或者栈中还有节点等待处理（!stk.isEmpty()），循环就会继续。
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root); //将当前 root 节点压入栈中。
                root = root.left;
            }
            // 当内层 while 循环结束时，root 已经变成了 null。这意味着我们已经到达了当前路径的最左端。
            // 我们从栈中弹出这个节点，并将其赋值给 root。
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * Morris 方法
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        InorderTraversal traversal = new InorderTraversal();

        // 创建测试二叉树
        //       1
        //        \
        //         2
        //        /
        //       3
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        // 执行中序遍历
        List<Integer> result = traversal.inorderTraversal1(root);

        // 输出结果
        System.out.println("Inorder Traversal Result: " + result); // 预期输出 [1, 3, 2]
    }
}
