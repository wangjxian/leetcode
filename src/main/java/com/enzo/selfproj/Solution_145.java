package com.enzo.selfproj;

//二叉树的后序遍历

//https://leetcode-cn.com/problems/binary-tree-postorder-traversal/


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution_145 {

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
     * 使用迭代方式来实现后序遍历
     * <p>
     * 左孩子----右孩子----父亲
     *
     * 出栈：左，右，根
     * 入栈：根，右，左
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {

        //异常判断
        if (root == null){
            return new ArrayList<Integer>();
        }

        //利用栈的数据结构
        //栈顶一定是最深的左孩子，最后入栈，
        //根是最先入栈
        //先
        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode temp = root;

        while (temp!= null){
            stack.push(temp);//根入栈

            //存在子树
            while (temp.left != null || temp.right != null){
                if (temp.right != null){
                    stack.push(temp.right);
                }
                if (temp.left != null){
                    stack.push(temp.left);
                }
            }

            //没有子树了，开始出栈
            TreeNode node = stack.pop();





        }

        return null;



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
