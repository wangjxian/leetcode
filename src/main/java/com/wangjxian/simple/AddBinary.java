package com.wangjxian.simple;

import java.math.BigInteger;

/**
 * 二进制求和
 *
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * @author wangjxian
 */
public class AddBinary {

    public static String addBinary(String a, String b) {

        return new BigInteger(a, 2).add(new BigInteger(b, 2)).toString(2);
    }


    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        String s = addBinary(a, b);
        System.out.println(s);
    }
}
