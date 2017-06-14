package com.csms;

import java.sql.*;

public class DBLink {
	private static final String URL = "jdbc:mysql://localhost:3306/airplane";

	private static final String NAME = "root";

	private static final String PASSWORD = "root";

	private static Connection conn = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(URL, NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 

	public static Connection getConn() {
		return conn;
	}
}