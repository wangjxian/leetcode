package com.wangjxian.learning;

import java.util.Stack;

/**
 * <p>
 * 括号匹配
 * </p>
 *
 * @author wangjiaxian
 * @since 2021/5/11
 */
public class BracketMatch {


  public static void main(String[] args) {
    BracketMatch bracketMatch = new BracketMatch();
    System.out.println(bracketMatch.match("()()()(())"));
    System.out.println(bracketMatch.match("(("));
    System.out.println(bracketMatch.match("()()(((())))"));
    System.out.println(bracketMatch.match("(()"));
    System.out.println(bracketMatch.match("(()))"));
    System.out.println(bracketMatch.match("((())())"));
  }


  public boolean match(String pattern){
    if (pattern == null || pattern.length() == 0){
      return false;
    }
    Stack<Character> stack = new Stack<>();
    for (char c : pattern.toCharArray()) {
      switch (c){
        case '(':
          stack.push(c);
          break;
        case ')':
          if (stack.isEmpty() || stack.pop() != '('){
            return false;
          }
          break;
        default:
          break;
      }
    }
    return stack.isEmpty();
  }
}
