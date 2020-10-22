package com.enzo.selfproj;

//二叉树的中序遍历

//https://leetcode-cn.com/problems/binary-tree-inorder-traversal/


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution_94 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 使用迭代方式来实现中序遍历
     * <p>
     * 左孩子--父亲---右孩子
     *
     * @param root
     * @return
     */

    public static List<Integer> inorderTraversal(TreeNode root) {
        //异常判断
        if (root == null) {
            return new ArrayList<Integer>();
        }


        List<Integer> list = new ArrayList<Integer>();

        //需要依赖一个栈 这样的额外空间
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //沿着左子树一直下探，栈顶是最深的左孩子节点，直到没有左子树了，开始回溯，到根，再压入右孩子
        TreeNode temp = root;

        while (temp != null || !stack.isEmpty()) {
            //直到没有左子树了，跳出循环
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            //开始出栈
            TreeNode node = stack.pop();
            list.add(node.val);
            temp = node.right;
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.right = t2;
        t2.left = t3;

        System.out.println(inorderTraversal(t1));
    }
}
