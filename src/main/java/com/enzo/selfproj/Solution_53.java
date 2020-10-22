package com.enzo.selfproj;


//https://leetcode-cn.com/problems/maximum-subarray/
public class Solution_53 {


    public  static int maxSubArray(int[] nums) {

        //dp:
        //令dp[i] 代表 num[0]---num[i]这个子数组的最大和。
        //那么dp[i]=Math.max(dp[i-1]+ num[i], num[i])
        //(为什么是比较dp[i-1]+num[i] 和 num[i]？
        //这里是因为考虑num[i]是加入原有的数组序列，或者是自成一个独立数组

        //考虑边界
        //dp[0]=0;

        int count=nums[0];
        int pre = 0;//dp[i]

        for (int n:nums){
            pre = Math.max(pre+n, n);
            count = Math.max(count, pre);
        }

        return count;
    }
}
