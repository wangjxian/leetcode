package com.enzo.selfproj;

//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
//
// 示例:
//
// 输入:
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4

//https://leetcode-cn.com/problems/maximal-square/
public class Solution_221 {


    public static void main(String[] args) {

    }

    public static int maximalSquare(char[][] matrix) {
        return s_dp(matrix);
    }


    /**
     * DP解法
     *
     * 受到以下启发
     * https://leetcode-cn.com/problems/maximal-square/solution/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
     * @return
     */
    public static int s_dp(char[][] matrix){
        //异常判断
        if (matrix == null || matrix.length<=0 || matrix[0].length<=0){
            return 0;
        }

        int height = matrix.length;
        int width = matrix[0].length;
        int [][] dp= new int[height+1][width+1];
        int maxSide=0;

        for (int  row = 0;row<height;row++){
            for (int column =0;column<width;column++){

                if (matrix[row][column] =='1'){
                    dp[row+1][column+1]= Math.min(Math.min(dp[row][column], dp[row][column+1]), dp[row+1][column]) +1;
                    maxSide = Math.max(maxSide, dp[row+1][column+1]);
                }
            }
        }

        return maxSide*maxSide;

    }



}
