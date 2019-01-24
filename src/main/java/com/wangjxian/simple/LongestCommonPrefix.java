package com.wangjxian.simple;

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * @author wangjxian
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs){
        if (strs.length == 0) {
            return "";
        }

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if(i == strs[0].length()){
                return sb.toString();
            }
            char c = strs[0].charAt(i);
            for (String str : strs){
                if (str.length() == i || str.charAt(i) != c){
                    return sb.toString();
                }
            }
            sb.append(c);
            i++;
        }
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strings));
    }

}
