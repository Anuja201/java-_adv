package com.dkte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class jdbcmain {
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/aids2";
	public static final String DB_USER = "root";
	public static final String DB_PASSWD = "Anuja@2005";
	
	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD)) {
			String sql = "INSERT INTO products VALUES (default, ?, ?)";
			try(PreparedStatement stmt = con.prepareStatement(sql)) {
				System.out.print("Enter Product Name: ");
				String name = sc.next();
				System.out.print("Enter Product Price: ");
				double price= sc.nextDouble();
				stmt.setString(1, name);
				stmt.setDouble(2, price);
				int count = stmt.executeUpdate();
				System.out.println("Rows Inserted: " + count);
			} // stmt.close();
		} // con.close();
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main1(String[] args) {
		try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD)) {
			String sql = "SELECT * FROM products";
			try(PreparedStatement stmt = con.prepareStatement(sql)) {
				try(ResultSet rs = stmt.executeQuery()) {
					while(rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						double price = rs.getDouble("price");
						System.out.println(id + ", " + name + ", " + price);
					}
				} // rs.close();
			} // stmt.close();
		} // con.close(); 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}














