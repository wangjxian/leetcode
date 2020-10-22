package com.wangjxian.simple;

//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
// 说明：每次只能向下或者向右移动一步。 
//
// 示例: 
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
// 
// Related Topics 数组 动态规划 
// 👍 694 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class minPathSum {


    public int minPathSum2(int[][] grid){
        int high = grid.length;
        int weigh = grid[0].length;
        int[] dp = new int[weigh];

        for (int i=0;i<weigh;i++){
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] =0;

        for (int i=0;i<high;i++){
            dp[0] = dp[0] + grid[i][0];
            for(int j=1;j<weigh;j++){
                dp[j] = Math.min(dp[j-1],dp[j])+grid[i][j];
            }
        }
        return dp[weigh-1];
    }

    public int minPathSum(int[][] grid) {
        int high = grid.length;
        int weigh = grid[0].length;


        int[][] dp = new int[high][weigh];

        dp[0][0] = grid[0][0];

        for (int i=1;i<high;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j=1;j<weigh;j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        for (int i=1;i<high;i++){
            for(int j=1;j<weigh;j++){
                dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j]) + grid[i][j];
            }
        }
        return dp[high-1][weigh-1];
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1,3,1},
            {1,5,1},
            {4,2,1}
        };

        System.out.println(new minPathSum().minPathSum(grid));
        System.out.println(new minPathSum().minPathSum2(grid));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
