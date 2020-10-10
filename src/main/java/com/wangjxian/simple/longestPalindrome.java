package com.wangjxian.simple;//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//
// P(i,j) = P(i+1,j-1) and S(i)==S(j)
//
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2775 👎 0


class longestPalindrome {

    public String longestPalindrome(String s) {

        if (s==null || "".equals(s))
            return "";
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (length < 2)
            return s;
        int right = 0;
        int max = 1;
        boolean[][] dp = new boolean[length][length];
        for (int i=0;i<length;i++){
            dp[i][i] = true;
        }
        for (int j=1;j<length;j++){
            for (int i =0;i<j;i++){
                if (s.charAt(i) != s.charAt(j)){
                    dp[i][j]=false;
                }else {
                    if (j-i<3){
                        dp[i][j]=true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if (dp[i][j] && j-i+1>max){
                    max = j-i+1;
                    right=i;
                }
            }
        }
        return s.substring(right,right+max);
    }

    public static void main(String[] args) {
        System.out.println(new longestPalindrome().longestPalindrome("babad"));
    }
}
