package com.enzo.selfproj;

//二叉树的前序遍历

//https://leetcode-cn.com/problems/binary-tree-preorder-traversal/


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution_144 {

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
     * 使用迭代方式来实现前序遍历
     * @param root
     * @return
     */

    public static List<Integer> preorderTraversal(TreeNode root) {
        //需要依赖一个栈 这样的额外空间

        //异常判断
        if (root == null){
            return new ArrayList<Integer>();
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);



        List<Integer> list = new ArrayList<Integer>();
        while (!stack.isEmpty()){

            //父亲节点先出栈。
            TreeNode node = stack.pop();
            list.add(node.val);
            //先压入右孩子，再压入左孩子。因为这样出栈的时候才能保证  是左-->右

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }



        }

        return list;

    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.right = t2;
        t2.left =t3;

        System.out.println(preorderTraversal(t1));
    }
}
