package com.book.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.book.dao.ILoginDAO;
import com.book.util.DBConnectivity;

public class LoginDAOImpl implements ILoginDAO {

	@Override
	public boolean validateLogin(String emailId, String password) {
		String query = "select * from customer where emailId=? and password=?";
		ResultSet resultSet = null;
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);

			// select * from customer where emailId='ketaki123@gmail.com' and
			// password='Ketaki123';

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
