package com.dkte;

import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Operation extends JFrame {
	private JLabel num1;
	private JTextField num1text;
	private JLabel num2;
	private JTextField num2text;
	private JButton addButton;
	private JButton substrButton;
	private JButton exitButton;
	private JLabel resultLabel;
	
	public Operation() {
		this.setTitle("Assignment");
		this.setSize(400,300);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		num1=new JLabel("Num1 :");
		num1.setBounds(50, 50, 80, 30);
		this.add(num1);
		
		num1text=new JTextField();
		num1text.setBounds(100, 50, 200, 30);
		this.add(num1text);
		
		num2=new JLabel("Num2 :");
		num2.setBounds(50, 100, 80, 30);
		this.add(num2);
		
		num2text=new JTextField();
		num2text.setBounds(100, 100, 200, 30);
		this.add(num2text);
		
		addButton=new JButton("ADD");
		addButton.setBounds(50, 150, 80, 30);
		this.add(addButton);
		
		substrButton=new JButton("Substract");
		substrButton.setBounds(150, 150, 80, 30);
		this.add(substrButton);
		
		exitButton=new JButton("Exit");
		exitButton.setBounds(250, 150, 80, 30);
		this.add(exitButton);
		
		resultLabel = new JLabel("Result : ");
        resultLabel.setBounds(50, 200, 300, 30);
        this.add(resultLabel);
		
		addButton.addActionListener((e) ->{
			try{
				int n1=Integer.parseInt(num1text.getText());
				int n2=Integer.parseInt(num2text.getText());
				int result= n1 + n2;
				resultLabel.setText("Result :"+result);
			}catch(Exception ex) {
				System.out.println("Invalid");
			}
		});
		
		substrButton.addActionListener((e) ->{
			try{
				int n1=Integer.parseInt(num1text.getText());
				int n2=Integer.parseInt(num2text.getText());
				int result= n1 - n2;
				resultLabel.setText("Result :"+result);
			}catch(Exception ex) {
				System.out.println("Invalid");
			}
		});
		
		exitButton.addActionListener((e) ->{
			this.dispose();
		});
	}
	
}
