package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
	
	private Connection con;

	public Connection connect() {
		String url = "jdbc:mysql://localhost:3306/ecommerce";
		String username = "root";
		String password = "12345678";

		System.out.println("Connecting database...");

		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return con;
	}

	public void close() {
		if (con != null) {
			try {
				con.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void insert(String databaseName, String input) {
	
	}
}
