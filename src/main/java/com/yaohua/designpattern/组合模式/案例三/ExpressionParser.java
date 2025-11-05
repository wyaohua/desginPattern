package com.yaohua.designpattern.组合模式.案例三;

import com.yaohua.designpattern.组合模式.案例三.基础类.AddExpress;
import com.yaohua.designpattern.组合模式.案例三.基础类.DevisionyExpress;
import com.yaohua.designpattern.组合模式.案例三.基础类.Expression;
import com.yaohua.designpattern.组合模式.案例三.基础类.MultiplyExpress;
import com.yaohua.designpattern.组合模式.案例三.基础类.NumberExpress;
import com.yaohua.designpattern.组合模式.案例三.基础类.SubstractExpress;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 文件名：ExpressionParser
 * 作者：huahua
 * 时间：2025/11/6 00:23
 * 描述  表达式解析器
 */
public class ExpressionParser {

    //中缀表达式
    private final  String infixExpression;

    //我们需要一次遍历中缀表达式 所以需要一个指针;
    int pointer = 0;

    public ExpressionParser(String infixExpression) {
        this.infixExpression = infixExpression;
    }


    /**
     * 将中缀表达式 转化为后缀表达式；
     * @return
     */
    public List<String> toSuffix(){
        List<String> suffix = new ArrayList<>(); //结果
        LinkedList<String> stack = new LinkedList<>();//辅助栈；

            //遍历中缀表达式，
        while (pointer < infixExpression.length()){
            char c =infixExpression.charAt(pointer);


            if ( c == '('){  //1. 遇到左括号，入栈

                stack.addLast(c +"");

            }else if( c == ')'){  //2.遇到右括号，弹出栈顶元素加入结果，直到遇到左括号；并且移除左括号；

                while(!stack.getLast().equals("(")){
                    suffix.add(stack.removeLast());
                }
                stack.removeLast();
            }else if (c == '*' || c == '/'){ //3. 遇到乘除法，弹出栈顶元素加入结果，直到遇到加减法或者左括号；
                while (!stack.isEmpty() && (stack.getLast().equals("*") || stack.getLast().equals("/"))){
                    suffix.add(stack.removeLast());
                }
                stack.addLast(c + "");
            }else if (c == '+' || c == '-'){ //4. 遇到加减法，如果栈顶是操作符就加入结果，直到遇到其他结束循环，将当前元素加入到栈中；
                while (topIsOperator(stack)){
                    suffix.add(stack.removeLast());
                }
                stack.addLast(c + "");

            }else if (Character.isDigit(c)){ //5. 遇到数字，加入结果；
               StringBuilder stringBuilder = new StringBuilder();
               while (pointer < infixExpression.length() && Character.isDigit(infixExpression.charAt(pointer))){
                   stringBuilder.append(infixExpression.charAt(pointer));
                   pointer++;
               }
               pointer--;
               suffix.add(stringBuilder.toString());
            }else{
                throw new RuntimeException("非法字符");
            }


            pointer++;
        }
        //把辅助栈的剩余元素都弄到结果中；
        while (!stack.isEmpty()){
            suffix.add(stack.removeLast());
        }


        return suffix;
    }




    //将后缀表达式变为 一个真正的表达式
    public Expression toExpression(){
        List<String> suffix = this.toSuffix();
        LinkedList<Expression>  stack = new LinkedList<>();


        for (String item : suffix) {
            if (item.equals("+")){
                Expression right = stack.removeLast();
               stack.addLast(new AddExpress(stack.removeLast(),right));
            }else if (item.equals("-")){
                Expression right = stack.removeLast();
                stack.addLast(new SubstractExpress(stack.removeLast(),right));
            }else if (item.equals("*")){
                Expression right = stack.removeLast();
                stack.addLast(new MultiplyExpress(stack.removeLast(),right));
            }else if (item.equals("/")){
                Expression right = stack.removeLast();
                stack.addLast(new DevisionyExpress(stack.removeLast(),right));
            }else{
                //数字
                int value = Integer.parseInt(item);
                stack.addLast(new NumberExpress(value));
            }
        }
            //最后一定就剩下一个表达式 就是我们要返回的；
            return stack.removeLast();
    }








    //判断栈顶元素是不是操作符；
    private boolean topIsOperator(LinkedList<String> stack){
        if (stack.isEmpty()) {
            return false;
        }

        return Set.of("+","-","*","/").contains(stack.getLast());

    }
}
