package com.wangjxian.simple;

import java.util.*;

/**
 * 二叉树的层次遍历 II
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 *
 * @author wangjxian
 */
public class LevelOrderBottom {

    public class TreeNode {
        int val;

        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
        /**
         * 广度优先遍历
         *
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         *
         *    3先入队列，然后 出队，
         *    9，20 入队，9出队，20 出队，15，7 入队
         * @param  root
         * @return
         */
        public List<List<Integer>> levelOrderBottom2(TreeNode root) {
            List<List<Integer>> lists = new ArrayList<List<Integer>>();
            //空树
            if (root == null){
                return lists;
            }
            //1.先把根节点放入队列，
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            //2.队列非空 表面 还有节点
            while (!queue.isEmpty()){
                //记录当前层 有多少节点
                int size = queue.size();
                List<Integer> temp = new ArrayList<Integer>();
                for (int i = 0; i < size; i++){
                    TreeNode current = queue.poll();
                    temp.add(current.val);
                    if (current.left != null){
                        queue.add(current.left);
                    }
                    if (current.right != null){
                        queue.add(current.right);
                    }
                }
                //将每层的list 放入list的首位
                lists.add(0,temp);
            }
            return lists;
        }

        /**
         * 递归。
         * 用一个level表示递归到哪一层 然后递归到下一层
         * @param  root
         * @return
         */
         public List<List<Integer>> levelOrderBottom(TreeNode root){
             List<List<Integer>> list = new ArrayList<List<Integer>>();
             addVal(list,0,root);
             Collections.reverse(list);
             return list;
         }

         private void addVal(List<List<Integer>> list, int level, TreeNode root){
             //节点为空 ，停止
             if (root == null){
                 return ;
             }
             //把level层 添加到 list中
             if (list.size() < level + 1){
                 list.add(new ArrayList<Integer>());
             }
             //把level层的节点 的val 添加到当前level的 list中
             list.get(level).add(root.val);
             //递归 左右子节点
             addVal(list,level+1,root.left);
             addVal(list,level+1,root.right);
         }

}
