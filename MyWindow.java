package com.dkte.swing1;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyWindow extends JFrame{
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel addrLabel;
	private JTextArea addrArea;
	private JLabel genderLabel;
	private JRadioButton malebutton,femalebutton;
	private ButtonGroup buttongroup;
	private JLabel languLabel;
	private JComboBox<String> lanuageBox;
	private JButton submitbutton;
	
	public MyWindow() {
		this.setSize(600,400);
		this.setTitle("CV");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
		nameLabel=new JLabel("Name :");
		nameLabel.setBounds(100, 50, 100, 30);
		this.add(nameLabel);
		
		nameField=new JTextField();
		nameField.setBounds(200, 50, 200, 30);
		this.add(nameField);
		
		addrLabel=new JLabel("Address :");
		addrLabel.setBounds(100, 100, 100, 30);
		this.add(addrLabel);
		
		addrArea=new JTextArea();
		addrArea.setBounds(200, 100, 200, 80);
		this.add(addrArea);
		
		genderLabel=new JLabel("Gender :");
		genderLabel.setBounds(100, 200, 100, 30);
		this.add(genderLabel);
		
		buttongroup=new ButtonGroup();
		
		malebutton=new JRadioButton("male");
		malebutton.setBounds(200, 200, 80, 30);
		this.add(malebutton);
		buttongroup.add(malebutton);
		
		femalebutton=new JRadioButton("female");
		femalebutton.setBounds(300, 200, 80, 30);
		this.add(femalebutton);
		buttongroup.add(femalebutton);
		
		languLabel=new JLabel("Lanuage :");
		languLabel.setBounds(100, 250, 100, 30);
		this.add(languLabel);
		
		String[] lang= {"Marathi","Hindi","English","Canady","France","Japaneses"};
		lanuageBox=new JComboBox<>(lang);
		lanuageBox.setBounds(200, 250, 200, 30);
		this.add(lanuageBox);
		
		submitbutton=new JButton("Submit");
		submitbutton.setBounds(210, 310, 80, 30);
		this.add(submitbutton);
		
		ActionListener listener=(e) ->{
			String name=nameField.getText();
			String addr=addrArea.getText();
			String gender="";
			if(malebutton.isSelected()) {
				gender="Male";
			}else if(femalebutton.isSelected()) {
				gender="Female";
			}
			String langu=(String) lanuageBox.getSelectedItem();
			String message="Name :"+ name+"\nAddress :"+addr+"\nGender :"+gender+"\nLanguage :"+langu;
//			System.out.println(message);
//			JOptionPane.showMessageDialog(this, message);//ok botton
//			JOptionPane.showConfirmDialog(this, message);// yes ,no,cancel button
			String age=JOptionPane.showInputDialog(this, "Enter age");//single user input
		};
		submitbutton.addActionListener(listener);
	}
}
