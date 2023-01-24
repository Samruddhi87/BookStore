package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectivity {
	/**
	 * This method is used to connect to database. It returns connection object if
	 * connected to database, null otherwise
	 * 
	 * @return connection
	 * @throws ClassNotFoundException 
	 */
	public static Connection makeConnection()  {
		 
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bookdb?user=root&password=root@123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
