package com.wangjxian.learning;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <p>
 * 计算表达式
 * </p>
 *
 * @author wangjiaxian
 * @since 2021/5/11
 */
public class Calculator {

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    System.out.println(calculator.evaluate("22/11-1+2*3+4*1"));
    System.out.println(calculator.evaluate("1+2+(2+1)*3+4*1"));
  }

  private interface Operator {
    Character ADD = '+';
    Character SUB = '-';
    Character MUL = '*';
    Character DIV = '/';
    Character L_P = '(';
    Character R_P = ')';
    Character EOE = '\0';
  }

  private final static Map<Character, Integer> optIndexMap = new HashMap<>();

  static {
    optIndexMap.put(Operator.ADD, 0);
    optIndexMap.put(Operator.SUB, 1);
    optIndexMap.put(Operator.MUL, 2);
    optIndexMap.put(Operator.DIV, 3);
    optIndexMap.put(Operator.L_P, 4);
    optIndexMap.put(Operator.R_P, 5);
    optIndexMap.put(Operator.EOE, 6);
  }

  private final char[][] order = {
             //   +    -    *    /    (    )   \0
      /* + */   {'>', '>', '<', '<', '<', '>', '>'},
      /* - */   {'>', '>', '<', '<', '<', '>', '>'},
      /* * */   {'>', '>', '>', '>', '<', '>', '>'},
      /* / */   {'>', '>', '>', '>', '<', '>', '>'},
      /* ( */   {'<', '<', '<', '<', '<', '=', '>'},
      /* ) */   {' ', ' ', ' ', ' ', ' ', ' ', ' '},
      /* \0 */  {'<', '<', '<', '<', '<', ' ', '='},
  };


  public int evaluate(String pattern) {

    pattern = pattern +"\0";

    Stack<Integer> opnd = new Stack<>();
    Stack<Character> optr = new Stack<>();
    optr.push(Operator.EOE);
    char[] chars = pattern.toCharArray();
    int current = 0;
    while (optr.peek() != Operator.EOE || current < chars.length-1) {
        if (Character.isDigit(chars[current])) {
          current = number(chars,current,opnd);
        } else {
          switch (orderBetween(optr.peek(), chars[current])) {
            case '<':
              optr.push(chars[current]);
              current++;
              break;
            case '=':
              optr.pop();
              current++;
              break;
            case '>':
              Character op = optr.pop();
              Integer opnd1 = opnd.pop();
              Integer opnd2 = opnd.pop();
              opnd.push(calcu(opnd1, op, opnd2));
              break;
            default:
              throw new RuntimeException("error order");
          }
        }
      }
    return opnd.pop();
  }

  private int number(char[] chars, int current,Stack<Integer> opnd) {
    StringBuilder sb = new StringBuilder();
    for (int i = current; i < chars.length; i++) {
      char ch = chars[i];
      if (!Character.isDigit(ch)){
        opnd.push(Integer.parseInt(sb.toString()));
        return i;
      }
      sb.append(ch);
    }
    return current;
  }

  private char orderBetween(Character pop, char current) {
    return order[optIndexMap.get(pop)][optIndexMap.get(current)];
  }

  private Integer calcu(Integer opnd1, Character op, Integer opnd2) {

    switch (op) {
      case '+':
        return opnd1 + opnd2;
      case '-':
        return opnd2 - opnd1;
      case '*':
        return opnd1 * opnd2;
      case '/':
        return opnd2 / opnd1;
    }

    throw new RuntimeException("error op");
  }


}
