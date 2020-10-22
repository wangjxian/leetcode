package com.wangjxian.simple;

import java.util.Stack;

public class calculate {

  public static void main(String[] args) {
//    System.out.println(new calculate().calculate("3+5*8-6*2+3"));
    System.out.println(new calculate().calculate("(1+(3+1)*2)*2+2"));
  }

  public int calculate(String str){
    Stack<Integer> number = new Stack<>();
    Stack<Character> symbol = new Stack<>();
    for (char c :str.toCharArray()){
      if (Character.isDigit(c)){
        //数字 压入 number栈
        number.push(Integer.parseInt(String.valueOf(c)));
      }else {
        //是括号
        if (c=='('){
          //压入括号
          symbol.push(c);
        }
        if (c == ')'){
          //计算括号
          while (!symbol.empty()){
            Character pop = symbol.pop();
            if (pop == '('){
              break;
            }
            Integer n1 = number.pop();
            Integer n2 = number.pop();
            number.push(calculate(n2,n1,pop));
          }
        }
        if (c=='+'||c=='-'||c=='*'||c=='/'){
        //是运算符
        if (symbol.empty()){
          //空压入
          symbol.push(c);
        }else if (c == '*' || c == '/'){
          //* / 压入 优于+-计算
          symbol.push(c);
        }else {
          //是 + -
          //先计算*/或者其他的+-再压入
          while (!symbol.empty()){
            Character peek = symbol.peek();
            if (peek=='('){
              break;
            }
            Integer n1 = number.pop();
            Integer n2 = number.pop();
            number.push(calculate(n2,n1,symbol.pop()));
          }
          symbol.push(c);
        }
      }
      }
    }
    //最后计算结果 最后若有多个计算必是 先 */ 再 +-
    while (!symbol.empty()){
      Character pop = symbol.pop();
      Integer n1 = number.pop();
      Integer n2 = number.pop();
      number.push(calculate(n2,n1,pop));
    }
    return number.pop();
  }


  public int calculate(int n1,int n2,Character s){
    switch (s){
      case '+':
        return n1+n2;
      case '-':
        return n1-n2;
      case '*':
        return n1*n2;
      case '/':
        return n1/n2;
      default:
        return 0;
    }
  }
}
