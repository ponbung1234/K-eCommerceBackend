package com.example.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private Connection con;

	public Connection connect() throws IOException {
		InetAddress addr = java.net.InetAddress.getLocalHost();
		String url;
		System.out.println(addr.getHostName());
		if (addr.getHostName().contains("Kasidis")) {
			url = "jdbc:mysql://localhost:13306/kecommerce";
		} else {

			url = "jdbc:mysql://kecommerce.c7c8bfjvbsfr.ap-southeast-1.rds.amazonaws.com:3306/kecommerce";

		}


		try {
			con = DriverManager.getConnection(url, "admin", "12345678");
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return con;
	}

	public void close() throws IOException {
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
