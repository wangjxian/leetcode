package com.wangjxian.simple;
//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"

//dp 为 i时最大长度
//1。当s[i]='('时 dp[i]=0
//2。当s[i]=')'.
//2。1当s[i-1]='('时。如......() ,则 dp[i] = dp[i-2]+2
//2。2当s[i-1]=')'时，日......)) ，则dp[i]= dp[i-1] + dp[i-dp[i-1]-2]
//       倒数第二个括号 + 与倒数第二个括号相匹配的'('之前的 有效括号长度
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划 
// 👍 1009 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class longestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null )
            return 0;
        int length = s.length();
        if ( length < 2)
           return 0;
        int max = 0;
        int[] dp = new int[length];
        for (int i=1;i<length;i++){
            char si = s.charAt(i);
            if (si==')') {
                char sj = s.charAt(i - 1);
                if (sj == '(')
                    if ( i >= 2)
                        dp[i] = dp[i-2] +2;
                    else
                        dp[i] = 2;
                else if(dp[i-1]>0) {
                    int j=i-dp[i-1]-1;
                    if(j>=0 && s.charAt(j)=='(')
                        dp[i]=dp[i-1]+2;
                        if(j>=1)
                            dp[i]=dp[i]+dp[j-1];
                }
                max = Math.max(dp[i],max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new longestValidParentheses().longestValidParentheses(")))))(((()("));
//        System.out.println(new longestValidParentheses().longestValidParentheses("(()))())("));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
