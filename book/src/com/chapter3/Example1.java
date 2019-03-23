package com.chapter3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Example1 extends JFrame{

	private JPanel p;
	private JButton btn1, btn2, btn3;
	
	public Example1() {
		super("流式布局");
		p = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 10, 15);
		p.setLayout(layout);
		btn1 = new JButton("红色");
		btn2 = new JButton("绿色");
		btn3 = new JButton("黄色");
		
		ButtonListerner listerner = new ButtonListerner();
		btn1.addActionListener(listerner);
		btn2.addActionListener(listerner);
		btn3.addActionListener(listerner);
		
		
		p.add(btn1);
		p.add(btn2);
		p.add(btn3);
		
		this.add(p);
		this.setSize(400, 400);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example1();
	}
	
	// 事件 监听器
	class ButtonListerner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source  = e.getSource();
			if(source == btn1) {
				p.setBackground(Color.RED);
			}else if(source == btn2){
				p.setBackground(Color.GREEN);
			}else if(source == btn3) {
				p.setBackground(Color.YELLOW);
			}
		}
	}
}
