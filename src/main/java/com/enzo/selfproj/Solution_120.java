package com.enzo.selfproj;

import java.util.ArrayList;
import java.util.List;


//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
//
//
//
// 例如，给定三角形：
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
//
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
//https://leetcode-cn.com/problems/triangle/
//
//
// 说明：
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
// Related Topics 数组 动态规划
// 👍 612 👎 0
public class Solution_120 {


    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(2);
        triangle.add(l1);

        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(3);
        l2.add(4);
        triangle.add(l2);

        List<Integer> l3 = new ArrayList<Integer>();
        l3.add(6);
        l3.add(5);
        l3.add(7);
        triangle.add(l3);

        List<Integer> l4 = new ArrayList<Integer>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        triangle.add(l4);
        System.out.println(s_1(triangle));

        System.out.println(minimumTotal(triangle));
    }
    public  static  int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size()<=0){
            return 0;
        }

        int height = triangle.size();
        int maxWidth = triangle.get(height-1).size();

        int[][] dp = new int[height+1][maxWidth];

        for (int i =1;i<=height;i++){

            List<Integer> integers = triangle.get(i - 1);
            for (int j = 0;j<integers.size();j++){
                if (j >= integers.size()-1){
                    dp[i][j] = dp[i-1][j] + integers.get(j);
                }else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j+1]) + integers.get(j);

                }
            }
        }

        int[] k = dp[height];int min = k[0];
        for (int a:k){
            min = Math.min(min, a);
        }

        return min;
    }


    /**
     * 解法
     * 自底向上
     * @param triangle
     * @return
     */
    public static int s_1(List<List<Integer>> triangle){
        // arr[i][j]存储的是从最底层( size+1 虚拟的层)到第i层第j个节点的累计路径和。
        int[][] arr = new int[triangle.size()+1][triangle.size()+1];


        for (int l = triangle.size() - 1;l>=0;l--){
            List<Integer> line =triangle.get(l);

            for (int index =0 ;index < line.size();index++){
                arr[l][index] = Math.min(arr[l+1][index], arr[l+1][index+1]) + line.get(index);
            }
        }


        return arr[0][0];
    }
}
