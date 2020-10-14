package com.jusuanqi;

import java.util.Stack;

    // 计算器运算逻辑函数
public class function {
    // 分割数字和字符
    public  static String[] strToArrays(String str){
        int strLength = str.length();  // 获取文本 输入长度
        int beginIndex = 0; int endIndex; // 计数器
        String[] Arrays = new String[strLength]; // 创建数组存储输入
        int arraysIndex = 0;
        for(int i = 0; i < strLength; i++){
            if(str.charAt(i)=='*'|| str.charAt(i)=='/'||
               str.charAt(i)=='+'||str.charAt(i)=='-'||
               str.charAt(i)=='('||str.charAt(i)==')'|| str.charAt(i)=='^'){
                endIndex = i - 1 ;
                if(beginIndex <= endIndex ){
                    Arrays[arraysIndex] = str.substring(beginIndex, endIndex+1);
                    Arrays[arraysIndex + 1] = String.valueOf(str.charAt(i));
                    arraysIndex += 2;
                }
                else{
                    Arrays[arraysIndex] = String.valueOf(str.charAt(i));
                    arraysIndex += 1;
                }
                beginIndex = i + 1;
            }
        }
        if(!isOper(String.valueOf(str.charAt(strLength - 1)))){
            Arrays[arraysIndex] = str.substring(beginIndex,strLength);
            arraysIndex += 1;
        }
        String[] Arrays2 = new String[arraysIndex];
        for(int i = 0; i < arraysIndex; i++) {
            Arrays2[i] = Arrays[i];
        }
        System.out.println(arraysIndex);
        return Arrays2;
    }
    // 建立堆栈，转换后缀表达式
    public static String[] toPostfixExpression(String[] Arrays){
        Stack<String> operStack= new Stack<>();
        String[] Arrays2=new String[Arrays.length];
        int tempIndex=0;
        for (int i=0;i<Arrays.length;i++){
            if(isOper(Arrays[i])){
                if (operStack.isEmpty()){                       //栈顶为空直接入栈
                    operStack.push(Arrays[i]);
                }
                else {
                    if (Arrays[i].equals("(")){                 //为（直接入栈
                        operStack.push(Arrays[i]);
                    }
                    else if (Arrays[i].equals(")")){            //为）栈顶出栈直到遇到（
                        while (!operStack.peek().equals("(")){
                            Arrays2[tempIndex]= operStack.pop();
                            tempIndex++;
                        }
                        operStack.pop();                        //（出栈
                    }
                    else {
                        if (operStack.peek().equals("(")){      //为（直接入栈
                            operStack.push(Arrays[i]);
                        }
                        else {                                  //运算符优先级高入栈
                            if (Priority(Arrays[i])>Priority(operStack.peek())){
                                operStack.push(Arrays[i]);
                            }
                            else {                              //运算符优先级相等或较低，栈顶出栈，直到运算符优先级高或栈为空
                                while (Priority(Arrays[i])<=Priority(operStack.peek())){
                                    Arrays2[tempIndex]= operStack.pop();
                                    tempIndex++;
                                    if (operStack.isEmpty()){   //栈顶为空直接入栈
                                        operStack.push(Arrays[i]);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }        // 若为字符
            else if (isNum(Arrays[i])){
                Arrays2[tempIndex]=Arrays[i];
                tempIndex++;
            }   // 若为数字，加入数组
        }

        while(!operStack.isEmpty()){
            Arrays2[tempIndex] = operStack.pop();
            tempIndex += 1;
        }
        String[] Arrays3 = new String[tempIndex];
        for(int i = 0; i < tempIndex ;i++){
            Arrays3[i] = Arrays2[i];
        }
        return Arrays3;
    }
    // 计算
    public static String toCompute(String[] Arrays){
        Stack<String> numStack = new Stack<>();     //创建了一个操作数的栈
        //遍历后缀表达式的字符串数组
        for(int i = 0; i < Arrays.length; i++){
            if(isNum(Arrays[i])){
                numStack.push(Arrays[i]);
            }
            else if(isOper(Arrays[i])){
                Double num2 = Double.parseDouble(numStack.pop());
                Double num1 = Double.parseDouble(numStack.pop());
                switch (Arrays[i]) {
                    case "+": {
                        double result = num1 + num2;
                        numStack.push(Double.toString(result));
                        break;
                    }
                    case "-": {
                        double result = num1 - num2;
                        numStack.push(Double.toString(result));
                        break;
                    }
                    case "*": {
                        double result = num1 * num2;
                        numStack.push(Double.toString(result));
                        break;
                    }
                    case "/": {
                        if (num2 == 0) {
                            return "除数不能为0";
                        }
                        double result = num1 / num2;
                        numStack.push(Double.toString(result));
                        break;
                    }
                    case "^": {
                        double result = Math.pow(num1, num2);
                        numStack.push(Double.toString(result));
                        break;
                    }
                    default:

                        break;
                }
            }
        }
        return numStack.pop();

    }
    // 判断是否为字符
    public static boolean isOper(String str){
        // 若为符号return
        return  str.equals("*") || str.equals("/") ||
                str.equals("+") || str.equals("-") ||
                str.equals("(") || str.equals(")") ||
                str.equals("^");
    }
    // 判断是否为数字
    public static boolean isNum(String str){
        // 若不为符号return
        return  !str.equals("*") && !str.equals("/") &&
                !str.equals("+") && !str.equals("-") &&
                !str.equals("(") && !str.equals(")") &&
                !str.equals("^");
    }
    // 字符运算优先级
    public static int Priority(String str){
        switch (str) {
            case "^":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 999;
        }
    }
}
