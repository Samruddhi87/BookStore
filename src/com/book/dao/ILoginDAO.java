package com.book.dao;

public interface ILoginDAO {
	/**
	 * This method validates email id and password in database
	 * 
	 * @param emailId
	 * @param password
	 * @return true if login validated, false otherwise
	 */
	boolean validateLogin(String emailId, String password);
}
