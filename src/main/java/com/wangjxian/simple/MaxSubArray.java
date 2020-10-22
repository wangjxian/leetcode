package com.wangjxian.simple;

/**
 * 最大子序列
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @author wangjxian
 */
public class MaxSubArray {

    /**
     * 动态规划
     *          {  S (n) + P(n-1) , P(n-1)>0
     *   P(n) =
     *           }  S(n) , P(n-1)<=0
     * @param  nums
     * @return  int
     */
    private static int maxSubArray(int[] nums) {
        int sum = 0;
        int res = nums[0];
        for (int num : nums){
            if (sum > 0){
                sum += num;
            }else {
                sum = num;
            }
            res = Math.max(res,sum);
        }
        return res;
    }
    /**
     * 分而治之
     * 一个最大的子序列只能有三种情况
     * 1。全部在左边
     * 2。全部在右边
     * 3。左右两边连续
     *
     *
     * @param  nums
     * @return
     */
    private static int maxSub(int[] nums){
        return maxSub(nums,0,nums.length-1);
    }

    private static int maxSub(int[] nums,int left, int right){
        //只有一个元素 递归结束
        if (left == right){
            return nums[left];
        }

        int center = (left + right) >> 1;
        int leftTempMax = nums[center], curLeft = 0 ;
        int rightTempMax = nums[center+1], curRight = 0;

        int leftMax = maxSub(nums,left,center);
        int rightMax = maxSub(nums,center+1,right);
        //这种为最大序列在中间
        //递归左边
        for (int i = center; i >= left; i-- ){
            curLeft += nums[i];
            if (curLeft > leftTempMax){
                leftTempMax = curLeft;
            }
        }
        //递归右边
        for (int i = center+1; i <= right; i++ ){
            curRight += nums[i];
            if (curRight > rightTempMax){
                rightTempMax = curRight;
            }
        }

        return max3num(leftMax,rightMax,leftTempMax+rightTempMax);
    }

    private static int max3num(int a, int b, int c){
        int temp = Math.max(a,b);
        return Math.max(temp,c);
    }

    public static void main(String[] args) {
        long beg = System.currentTimeMillis();
        int[] nums = {-2,-1};
        int a1 = maxSub(nums);
        long end1 =  System.currentTimeMillis();
        System.out.println(a1+"用时1："+(end1-beg));
        int a2 = maxSubArray(nums);
        long end2 =  System.currentTimeMillis();
        System.out.println(a2+"用时2："+(end2-end1));
    }
}
