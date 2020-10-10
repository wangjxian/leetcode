package com.wangjxian.simple;

////1 0 1 1 0
////1 1 1 1 1
////1 1 1 1 1
////1 0 0 1 0

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


// Related Topics 动态规划
//
//P(i,j) = min( P(i-1,j-1),P(i-1,p),P(i,j-1) ) + 1
//
class maximalSquare {


    public int maximalSquare1(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int high = matrix.length;
        int weight = matrix[0].length;
        int max = 0;
        int[][] dp = new int[high][weight];
        for (int i = 0 ;i<high;i++){
            for (int j=0;j<weight;j++){
                if (matrix[i][j] == 1){
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    }
                    else
                        dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1]) + 1;
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max * max;
    }

    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int high = matrix.length;
        int weight = matrix[0].length;
        int max = 0;
        int[] dpw = new int[weight];
        for (int i = 0 ;i<high;i++){
            int temp = 0;
            for (int j=0;j<weight;j++){
                int up = dpw[j];
                if (matrix[i][j] == 1){
                    if (i == 0 || j == 0) {
                        dpw[j] = 1;
                    }
                    else
                        dpw[j] = Math.min(Math.min(dpw[j-1],dpw[j]),temp) + 1;
                }
                else
                    dpw[j] = 0;
                temp = up;
                max = Math.max(max,dpw[j]);
            }
        }
        return max * max;
    }


    public static void main(String[] args) {
        char[][] matrix = {{1,0,1,1,0},{1,1,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};

        System.out.println( new maximalSquare().maximalSquare1(matrix));
        System.out.println( new maximalSquare().maximalSquare2(matrix));
    }
}