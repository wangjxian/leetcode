package com.wangjxian.learning;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

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

  //逆波兰表达式分隔符
  private final static String RPN_SPILT = ",";
  //数字正则
  private final static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

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

  //添加到rpn中
  private void appendRpn(StringBuilder rpn,Object o){
    rpn.append(o).append(RPN_SPILT);
  }

  //计算rpn表达式结果
  public int evaluateRpn(String rpn){
    //构造操作树栈
    Stack<Integer> opnd = new Stack<>();
    //分割
    String[] expr = rpn.split(RPN_SPILT);
    for (String str : expr) {
      //判断是否是数字
      if (NUMBER_PATTERN.matcher(str).matches()){
        //压入
        opnd.push(Integer.parseInt(str));
      }else {
        //取出两个元素
        Integer opnd1 = opnd.pop();
        Integer opnd2 = opnd.pop();
        //计算并压入结果
        opnd.push(calcu(opnd1, str , opnd2));
      }
    }
    return opnd.pop();
  }

  public int evaluate(String pattern) {

    //尾部加一个哨兵
    pattern = pattern +"\0";
    //RPN表达式
    StringBuilder rpn = new StringBuilder();

    //数字栈和操作符栈
    Stack<Integer> opnd = new Stack<>();
    Stack<Character> optr = new Stack<>();
    //栈加一个哨兵
    optr.push(Operator.EOE);
    char[] chars = pattern.toCharArray();
    int current = 0;
    //当操作符栈顶不是哨兵 或者 当前遍历的长度未达到表达式长度
    while (optr.peek() != Operator.EOE || current < chars.length-1) {
      char currentChar = chars[current];
      if (Character.isDigit(currentChar)) {
        //压入连续的数字，并返回最新的运算符位置
          current = number(chars,current,opnd);
          //将栈顶元素添加到rpn表达式中
           appendRpn(rpn,opnd.peek());
        } else {
          //比较运算符优先级
          switch (orderBetween(optr.peek(), currentChar)) {
            case '<':
              //当前运算符比栈顶运算符级别高，入栈
              optr.push(currentChar);
              current++;
              break;
            case '=':
              //级别相同，为后括号匹配前括号
              optr.pop();
              current++;
              break;
            case '>':
              //栈顶级别高。取出两个元素计算并压入结果
              Character op = optr.pop();
              //只有弹出操作符时,才加入到rpn中
              appendRpn(rpn,op);
              Integer opnd1 = opnd.pop();
              Integer opnd2 = opnd.pop();
              opnd.push(calcu(opnd1, String.valueOf(op), opnd2));
              break;
            default:
              throw new RuntimeException("error order");
          }
        }
      }
    System.out.println("rpn:"+rpn.toString());
    System.out.println("rpn result:"+evaluateRpn(rpn.toString()));
    //当运算符栈只有哨兵，并且已经遍历结束返回结果
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

  //比较顺序、通过提前构造的二维数组得出两个运算符的顺序关系
  private char orderBetween(Character pop, char current) {
    return order[optIndexMap.get(pop)][optIndexMap.get(current)];
  }

  private Integer calcu(Integer opnd1, String op, Integer opnd2) {

    switch (op) {
      case "+":
        return opnd1 + opnd2;
      case "-":
        return opnd2 - opnd1;
      case "*":
        return opnd1 * opnd2;
      case "/":
        return opnd2 / opnd1;
    }

    throw new RuntimeException("error op");
  }


}
