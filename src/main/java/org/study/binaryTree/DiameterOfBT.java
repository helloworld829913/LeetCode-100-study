package org.study.binaryTree;

/**
 * 给你一棵二叉树的根节点，返回该树的 直径 。<br>
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 */
public class DiameterOfBT {

    /**
     * 方法1：深度优先
     * <p>
     * 思路：
     * 1. 一次深度优先遍历（DFS）完成。在遍历每个节点时，它同时进行两项工作：
     * 2. 计算当前节点为根的子树的深度（用于向上传递给父节点）。
     * 3. 更新全局变量，记录经过当前节点的最长路径的节点数量（左子树深度 + 右子树深度 + 1）。
     * 4. 最终，全局变量中存储的就是树中最长路径的节点数量，减去1即为直径（边的数量）。
     * <p>
     * 依据：
     * 1. 任意一条路径均可以被看作由某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接得到。
     * 2. 一条路径的长度为该路径经过的节点数减一，所以求直径（即求路径长度的最大值）等效于求路径经过节点数的最大值减一
     */
    int ans;// 记录在遍历过程中找到的最长路径的节点数量。

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;//初始设置为 1 (ans = 1;)，因为即使是空树，直径也是 0（0 条边），
        // 如果只有一个节点，直径也是 0（0 条边）。如果有一个节点，那么它自己就是一条路径（1 个节点），
        // 所以 ans 从 1 开始，最终会 ans - 1 得到边的数量。
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度，递归调用，计算左子树的最大深度。
        int R = depth(node.right); // 右儿子为根的子树的深度，递归调用，计算右子树的最大深度。
        ans = Math.max(ans, L + R + 1); // 计算d_node即L+R+1 并更新ans
        //一个节点的高度/深度等于它左右子树中较高的那个深度再加 1（加上它自己）。
        //这个返回值将用于其父节点的 L 或 R 的计算。
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }

    public static void main(String[] args) {
        // 构造一个测试用例: [1,2,3,4,5]
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        DiameterOfBT solution = new DiameterOfBT();
        int result = solution.diameterOfBinaryTree(root);
        System.out.println("二叉树的直径是: " + result); // 预期输出: 3 (路径是 4->2->1->3 或 5->2->1->3)
    }
}
