package com.wangjxian.simple;

/**
 * 将有序数组转换为二叉搜索树
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * @author wangjxian
 */
public class SortedArrayToBST {

    /**
     * Definition for a binary tree node.
     *
     */
     public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if ( (nums == null) ||nums.length == 0){
            return null;
        }
        return sortedArrayToBST(nums,0,nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int lo, int hi){
         if (lo > hi){
             return null;
         }
         //递归结束
         if (lo == hi){
             return new TreeNode(nums[lo]);
         }
         //中间
         int mid = (lo + hi) >> 1;
         TreeNode root = new TreeNode(nums[mid]);
         root.left = sortedArrayToBST(nums,lo,mid-1);
         root.right = sortedArrayToBST(nums,mid+1,hi);
         return root;
    }
}
