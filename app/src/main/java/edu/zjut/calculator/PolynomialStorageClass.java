package edu.zjut.calculator;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolynomialStorageClass implements Serializable {

    /**
     * Transform a normal expression into a infix notion
     * @param NormalNotion the normal expression to be tranformed
     * @return infix notion
     */

    public static List<String> toInfixExpression(String NormalNotion) {
        List<String> InfixNotion = new ArrayList<>();
        int iterator = 0;
        StringBuilder ResultBuilder;
        char c;

        do {
            if ((c = NormalNotion.charAt(iterator)) < 48 || (c = NormalNotion.charAt(iterator)) > 57) {
                InfixNotion.add("" + c);
                iterator++;
            } else {
                ResultBuilder = new StringBuilder();
                while (iterator < NormalNotion.length() && (c = NormalNotion.charAt(iterator)) >= 48
                        && (c = NormalNotion.charAt(iterator)) <= 57) {
                    ResultBuilder.append(c);
                    iterator++;
                }
                InfixNotion.add(ResultBuilder.toString());
            }

        } while (iterator < NormalNotion.length());
        return InfixNotion;
    }

    /**
     * Transform an infix notion into an RPN (Reverse Polish Notion)
     * @param InfixNotion The infix notion to be transformed
     * @return The result RPN as List of String
     */

    public static List<String> parseSuffixExpression(List<String> InfixNotion) {
        Stack<String> EphemeralResult=new Stack<String>();
        Stack<String> OperatorStack=new Stack<String>();
        List<String> RPNResult = new ArrayList<String>();
        for (String InfixNotionElement : InfixNotion) {
            if (InfixNotionElement.matches("\\d+")) {
                RPNResult.add(InfixNotionElement);
            } else if (InfixNotionElement.equals("(")) {
                EphemeralResult.push(InfixNotionElement);
            } else if (InfixNotionElement.equals(")")) {

                while (!EphemeralResult.peek().equals("(")) {
                    RPNResult.add(EphemeralResult.pop());
                }
                EphemeralResult.pop();
            } else {
                while (EphemeralResult.size() != 0 && Operation.getValue(EphemeralResult.peek())
                        >= Operation.getValue(InfixNotionElement)) {
                    RPNResult.add(EphemeralResult.pop());
                }
                EphemeralResult.push(InfixNotionElement);
            }
        }
        while (EphemeralResult.size() != 0) {
            RPNResult.add(EphemeralResult.pop());
        }
        return RPNResult;
    }

    /**
     * Calculate the result of an RPN
     * @param ls the RPN to be calculated
     * @return computational result
     */

    public static int calculate(List<String> ls) {
        Stack<String> s=new Stack<String>();
        for (String str : ls) {
            if (str.matches("\\d+")) {
                s.push(str);
            } else {
                int b = Integer.parseInt(s.pop());
                int a = Integer.parseInt(s.pop());
                int result=0;
                switch (str) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                }
                s.push("" + result);
            }
        }
        System.out.println(s.peek());
        return Integer.parseInt(s.pop());
    }

    static class Operation {

        public static int getValue(@NonNull String operation){
            int result;
            final int ADDITION = 1;
            final int SUBTRACTION = 1;
            final int MULTIPLICATION = 2;
            final int DIVISION = 2;
            switch (operation){
                case "+":
                    result= ADDITION;
                    break;
                case "-":
                    result= SUBTRACTION;
                    break;
                case "*":
                    result= MULTIPLICATION;
                    break;
                case "/":
                    result= DIVISION;
                    break;
                default:
//                System.out.println("不存在该运算符");
                    result=0;
            }
            return result;
        }
    }

    /**
     * credit: https://www.cnblogs.com/chensongxian/p/7059802.html
     */
}
