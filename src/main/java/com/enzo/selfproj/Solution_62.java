package com.enzo.selfproj;


//https://leetcode-cn.com/problems/unique-paths/
public class Solution_62 {

    public static int uniquePaths(int m, int n) {

        //设start坐标为0,0，终点坐标为m,n；
        //令dp[m,n]为到达坐标为m,n的不同路径
        //其关联的格子为左边或者下方的格子
        //则可以推导出 dp[m,n] = dp[m-1,n] (左边) + dp[m,n-1] (下方)

        //边界: dp[m,0]=1 ,dp[0,n]=1



        //以下是官方给的解答

        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) dp[0][i] = 1;//边界填充
        for (int i = 0; i < m; i++) dp[i][0] = 1;//边界填充
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];


    }
}
