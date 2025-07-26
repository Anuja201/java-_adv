package com.dkte.swing2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MyWindow2 extends JFrame{
	private JButton Dialoglabel;
	
	public MyWindow2() {
		this.setTitle("My Window");
		this.setSize(800,600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
		Dialoglabel = new JButton("Show Dialoge");
		Dialoglabel.setBounds(280, 250, 200, 50);
		this.add(Dialoglabel);
		
		Dialoglabel.addActionListener((e) ->{
			Essay es=new Essay();
			es.setModal(true);
			es.setVisible(true);
			String message ="Title :"+es.getTitle()+"Essay :"+es.getEssay() ;
			JOptionPane.showInternalMessageDialog(es, message);
		});
	}

	
}
