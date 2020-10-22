package com.enzo.selfproj;

//最大连续1的个数
//https://leetcode-cn.com/problems/max-consecutive-ones/
public class Solution_485 {



    public static int findMaxConsecutiveOnes(int[] nums) {
        
        
        if (nums == null || nums.length<=0){
            return 0;
        }
        
        int max = 0;
        int count = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i] == 1){
                count++;
            }else {
                max = Math.max(max, count);
                count = 0;
            }
            
            
        }

        return Math.max(max, count);
    }
    
    
    
}
