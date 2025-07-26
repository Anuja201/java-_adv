package com.dkte;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProductDialog extends JDialog {
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel priceLabel;
	private JTextField priceTextField;
	private JButton submitButton;
	private Product product;
	
	public ProductDialog() {
		this.setTitle("Product");
		this.setSize(300, 250);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
		idLabel = new JLabel("Id");
		idLabel.setBounds(20, 20, 80, 30);
		this.add(idLabel);

		idTextField = new JTextField();
		idTextField.setBounds(100, 20, 150, 30);
		this.add(idTextField);
		
		nameLabel = new JLabel("Name");
		nameLabel.setBounds(20, 70, 80, 30);
		this.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(100, 70, 150, 30);
		this.add(nameTextField);

		priceLabel = new JLabel("Price");
		priceLabel.setBounds(20, 120, 80, 30);
		this.add(priceLabel);

		priceTextField = new JTextField();
		priceTextField.setBounds(100, 120, 150, 30);
		this.add(priceTextField);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(100, 170, 100, 30);
		this.add(submitButton);
		
		submitButton.addActionListener((e) -> {
			int id = Integer.parseInt(idTextField.getText());
			String name = nameTextField.getText();
			double price = Double.parseDouble(priceTextField.getText());
			this.product = new Product(id, name, price);
			this.dispose(); // close the dialog
		});
	}
	
	public Product getProduct() {
		return product;
	}
}
