package com.wangjxian.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径和
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2
 *
 * @author wangjxian
 */
public class HasPathSum {

    public static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        int val = root.val;

        if (root.left == null && root.right == null){
           return sum == val;
        }
        return hasPathSum(root.left,sum - val) || hasPathSum(root.right, sum - val);
    }




    /**
     *
     * 遍历出 所有路径
     *
     * 5-4-11-7
     * 5-4-11-2
     * 5-8-13
     * 5-8-4-1
     *
     * */

     private static List<List<Integer>> pathList = new ArrayList<>();
     private static List<Integer> tmp = new ArrayList<>();

     private static void allPath(TreeNode root) {
         if (root == null) {
             return;
         }
         tmp.add(root.val);

         if (root.right == null && root.left == null) {
             pathList.add(new ArrayList<>(tmp));
             tmp.remove(Integer.valueOf(root.val));
         }

//         if (root.left != null){

         allPath(root.left);
//        }
//         if (root.right != null){
             if (root.left != null){
                 int index = tmp.indexOf(root.val);
                 List<Integer> list = new ArrayList<>();
                 for (int i = 0;i <= index;i++){
                     list.add(tmp.get(i));
                 }
                 tmp.clear();
                 tmp.addAll(list);
             }
             allPath(root.right);
//         }
     }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.left = node6;
        node3.right = node7;
        node2.left = node4;
        node2.right = node5;
        node5.right = node8;
//        TreeNode root = new TreeNode(1);
        allPath(root);
        pathList.forEach(path->{
            path.forEach( x ->{
                System.out.print(x+" -> ");
                    }
            );
            System.out.println(" ");
        });
        System.out.println(has(22));

    }

    private static boolean has(int sum){

        for (List<Integer> path: pathList){
             int num = 0;
             for (Integer i : path){
                 num += i;
             }
             if (num == sum){
                 return true;
             }
         }
         return false;
    }

}
