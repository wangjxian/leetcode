package com.wangjxian.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class calculate2 {


  public static void main(String[] args) {
//    System.out.println(new calculate().calculate("3+5*8-6*2+3"));
    System.out.println(new calculate2().calculate("(1+2)*(3+4)"));
    System.out.println(new calculate2().calculate("(1*2+3)*4*5"));
    System.out.println(new calculate2().calculate("((9+10)*7*8+6)*1*2*(3+4+5)"));
  }

  public List<List<String>> calculate(String str){
    Stack<List<List<String>>> number = new Stack<>();
    Stack<Character> symbol = new Stack<>();
    for (char c :str.toCharArray()){
      if (Character.isDigit(c)){
        //数字 压入 number栈
        List<String> list= new ArrayList<>();
        list.add(String.valueOf(c));
        List<List<String>> list1 = new ArrayList<>();
        list1.add(list);
        number.push(list1);
      }else {
        //是括号
        if (c=='('){
          //压入括号
          symbol.push(c);
        }
        if (c == ')'){
          //计算括号
          calculateAndPush(number, symbol);
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
            List<List<String>> n1 = number.pop();
            List<List<String>> n2 = number.pop();
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
      List<List<String>> n1 = number.pop();
      List<List<String>> n2 = number.pop();
      number.push(calculate(n2,n1,pop));
    }
    return number.pop();
  }

  private void calculateAndPush(Stack<List<List<String>>> number, Stack<Character> symbol) {
    while (!symbol.empty()){
      Character pop = symbol.pop();
      if (pop == '('){
        break;
      }
      List<List<String>> n1 = number.pop();
      List<List<String>> n2 = number.pop();
      number.push(calculate(n1,n2,pop));
    }
  }


  public List<List<String>> calculate(List<List<String>> n1,List<List<String>> n2,Character s){
    List<List<String> > result = new ArrayList<>();
    switch (s){
      case '+':
        result.addAll(n1);
        result.addAll(n2);
        return result;
      case '*':
        for (List<String> list1 : n1) {
          for (List<String> list2 : n2) {
            List<String> sub = new ArrayList<>();
            sub.addAll(list1);
            sub.addAll(list2);
            result.add(sub);
          }
        }
        return result;
      default:
        return result;
    }
  }
}
