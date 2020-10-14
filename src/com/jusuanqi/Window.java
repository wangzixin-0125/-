package com.jusuanqi;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends Frame {
        private TextArea textArea; // 创建文本输入框
        private String str=""; // 输入
        // 窗口类
        public Window(){
            setTitle("简易计算器");  // 设置窗口名
            setSize(300,300);  // 设置窗口名大小
            setTextArea(str);  // 加入文本输入框
            setButtonArea();  // 加入按钮
            setMainFram();
        }
        // 文本输入框类 输入框属性设置
        private void setTextArea(String str){
            textArea=new TextArea(str,8,50,3); // 创建一个新的文本框
            textArea.setSize(500,100);  // 设置大小
            textArea.setFont(new Font("楷体", Font.BOLD, 50)); // 设置文本框字体
            textArea.setLocation(20,60);  // 文本框在界面位置
            this.add(textArea);
        }
        // 按钮类 通用按钮属性设置
        private void addButton(String string, int x, int y) {
            final Button btn = new Button(string);
            btn.setLocation(x, y);  // 按钮在界面的坐标
            btn.setSize(100,100);  // 按钮大小
            btn.setFont(new Font("楷体", Font.BOLD, 15));  // 字体设置
            btn.setBackground(Color.white);  // 背景颜色
            btn.setForeground(Color.black);  // 按钮颜色
            btn.addActionListener(e -> {
                str = str+string;
                textArea.setText(str);
            });  // 按钮输入监听
            this.add(btn);
        }
        // 界面布局类 添加按钮到界面中
        private void setButtonArea() {
            // 第一行
            addButton("7", 20,170);
            addButton("8", 120,170);
            addButton("9", 220,170);
            addButton("+", 320,170);
            addButton("/", 420,170);
            // 第二行
            addButton("4", 20,270);
            addButton("5", 120,270);
            addButton("6", 220,270);
            addButton("-", 320,270);
            addButton("*", 420,270);
            // 第三行
            addButton("1", 20,370);
            addButton("2", 120,370);
            addButton("3", 220,370);
            addButton("(", 320,370);
            addButton(")", 420,370);
            // 第四行
            addButton(".", 20,470);
            addButton("0", 120,470);
            addButton("^", 220,470);

            // "=" 等于按钮
            Button btn1=new Button("=");
            btn1.setLocation(420,470);
            btn1.setSize(100,100);
            btn1.setFont(new Font("楷体", Font.BOLD, 15));
            btn1.setBackground(Color.white);
            btn1.setForeground(Color.black);
            btn1.addActionListener(e -> {
                String result = function.toCompute(function.toPostfixExpression(function.strToArrays(str)));
                str=str+"=";
                str=str+result;
                textArea.setText(str);
            });
            this.add(btn1);

            // "C" 归零按钮
            Button btn2=new Button("C");
            btn2.setLocation(320,470);
            btn2.setSize(100,100);
            btn2.setFont(new Font("楷体", Font.BOLD, 15));
            btn2.setBackground(Color.white);
            btn2.setForeground(Color.black);
            btn2.addActionListener(e -> {
                str="";
                textArea.setText(str);
            });
            this.add(btn2);
        }
        // 界面设置类 界面属性设置
        private void setMainFram() {
            this.setLayout(null);  //
            this.setSize(550,600); // 界面默认大小
            this.setVisible(true);  // 界面可见
            this.setLocation(310, 340); // 窗口位置
            this.setResizable(true); // 窗口大小可变
            textArea.setEditable(false);
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        }
    }

