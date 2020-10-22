package com.wangjxian.simple;//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 说明：m 和 n 的值均不超过 100。 
//
// 示例 1: 
//
// 输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//                  { P(i-1,j)+P(i,j-1),  S(i,j) ==0;
//    P(i,j)    =
//                  0, S(i,j)==1          }
//
//
//输出: 2
//解释:
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
// Related Topics 数组 动态规划 
// 👍 424 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class uniquePathsWithObstacles {

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int high = obstacleGrid.length;
        int weight = obstacleGrid[0].length;
        int[] dp = new int[weight];

        dp[0] = obstacleGrid[0][0] == 1 ? 0:1;
        for (int i=0;i<high;i++) {
            for (int j = 0; j < weight; j++) {
                if (obstacleGrid[i][j] == 1){
                    dp[j] = 0;
                    continue;
                }
                if (j>=1 && obstacleGrid[i][j-1] == 0 ){
                        dp[j] += dp[j-1];
                }

            }
        }
        return dp[weight-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {


        int high = obstacleGrid.length;
        int weight = obstacleGrid[0].length;
        int[][] dp = new int[high][weight];

        dp[0][0] = obstacleGrid[0][0] == 1 ? 0:1;

        for (int i=1;i<weight;i++){
            if (obstacleGrid[0][i] == 1){
                dp[0][i] = 0;
            }else {
                dp[0][i] = dp[0][i-1];
            }
        }
//
        for (int i=1;i<high;i++){
            if (obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }else {
                dp[i][0] = dp[i-1][0];
            }
        }

        for (int i=1;i<high;i++){
            for (int j=1;j<weight;j++){
                if (obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
            }
        }

       return dp[high-1][weight-1];
    }


    public static void main(String[] args) {
        int[][] obstacleGrid = {
            {0,0,0},
            {1,0,1},
            {0,0,0}
        };

        System.out.println(new uniquePathsWithObstacles().uniquePathsWithObstacles(obstacleGrid));
        System.out.println(new uniquePathsWithObstacles().uniquePathsWithObstacles2(obstacleGrid));

    }


}
//leetcode submit region end(Prohibit modification and deletion)
