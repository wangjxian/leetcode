package com.enzo.selfproj;

//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
//
// 说明：
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//
// 示例 1:
//
// 输入: [2,2,3,2]
//输出: 3
//
//
// 示例 2:
//
// 输入: [0,1,0,1,0,1,99]
//输出: 99
// Related Topics 位运算
// 👍 423 👎 0
// 3m+1



public class Solution_137 {
    public static int singleNumber(int[] nums) {
        return  0;
    }


    public static void main(String[] args) {

        int[] ir = new int[4];
        ir[0] = 2;
        ir[1] = 2;
        ir[2] = 3;
        ir[3] = 2;

        System.out.println(singleNumber(ir));
    }
}
