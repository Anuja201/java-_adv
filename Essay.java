package com.dkte.swing2;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Essay  extends JDialog{ 

	private JLabel titlelab;
	private JTextField titlefield;
	private JTextArea areatext;
	private JScrollPane essayScroll;
	private JButton submitbutton;
	private String essay="";
	private String title="";
	public Essay() {
		this.setTitle("Essay");
		this.setSize(400,300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
		titlelab=new JLabel("Title :");
		titlelab.setBounds(100, 40, 80, 30);
		this.add(titlelab);
		
		titlefield=new JTextField();
		titlefield.setBounds(150, 40, 150, 30);
		this.add(titlefield);
		
		areatext=new JTextArea();
		essayScroll=new JScrollPane(areatext);
		essayScroll.setBounds(100, 80, 200, 100);
		this.add(essayScroll);
		
		submitbutton=new JButton("Submit");
		submitbutton.setBounds(150, 200, 80, 30);
		this.add(submitbutton);
		
		submitbutton.addActionListener((e) ->{
			this.title=titlefield.getText();
			this.essay=areatext.getText();
			this.dispose();
		});
	}
	public String getTitle() {
		return title;
	}
	public String getEssay() {
		return essay;
	}
}
