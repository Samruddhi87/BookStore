package com.book.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.ICustomerDAO;
import com.book.pojo.Customer;
import com.book.util.DBConnectivity;

public class CustomerDAOImpl implements ICustomerDAO {
	String query = "";

	// Connection connection = null;
	ResultSet resulSet = null;

	@Override
	public boolean addCustomer(Customer customer) {
		query = "insert into customer (firstName, lastName, address, contact, emailId, password) "
				+ "values (? ,? ,? ,? ,? ,? )";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getAddress());
			preparedStatement.setString(4, customer.getContact());
			preparedStatement.setString(5, customer.getEmailId());
			preparedStatement.setString(6, customer.getPassword());

			int n = preparedStatement.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		query = "update customer set firstName=?, lastName=?, address=?, address2=?, contact=?, emailId=?, password=? "
				+ "where customerId=?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			// set all other columns
			preparedStatement.setString(3, customer.getAddress());
			preparedStatement.setString(4, customer.getAddress2());
			preparedStatement.setString(5, customer.getContact());
			preparedStatement.setString(6, customer.getEmailId());
			preparedStatement.setString(7, customer.getPassword());
			preparedStatement.setInt(8, customer.getCustomerId());
			int n = preparedStatement.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(Integer customerId) {
		query = "delete from customer where customerId =?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, customerId);

			int n = preparedStatement.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// Implement this getAllCustomers();
		List<Customer> customerList = new ArrayList<>();

		query = "select customerId, firstname, lastName, address, address2, contact, emailId, password "
				+ "from customer  ";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			resulSet = preparedStatement.executeQuery();
			if (resulSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(resulSet.getInt(1));
				customer.setFirstName(resulSet.getString(2));
				customer.setLastName(resulSet.getString(3));
				customer.setAddress(resulSet.getString(4));
				customer.setAddress2(resulSet.getString(5));
				customer.setContact(resulSet.getString(6));
				customer.setEmailId(resulSet.getString(7));
				customer.setPassword(resulSet.getString(8));

				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != resulSet) {
				try {
					resulSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return customerList;
	}

	@Override
	public Customer viewCustomerByCustomerId(Integer customerId) {
		Customer customer = null;
		query = "select customerId, firstname, lastName, address, address2, contact, emailId, password "
				+ "from customer where customerId =? ";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, customerId);

			resulSet = preparedStatement.executeQuery();
			if (resulSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resulSet.getInt(1));
				customer.setFirstName(resulSet.getString(2));
				customer.setLastName(resulSet.getString(3));
				customer.setAddress(resulSet.getString(4));
				customer.setAddress2(resulSet.getString(5));
				customer.setContact(resulSet.getString(6));
				customer.setEmailId(resulSet.getString(7));
				customer.setPassword(resulSet.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != resulSet) {
				try {
					resulSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return customer;
	}

	@Override
	public Customer viewCustomerByEmailId(String emailId) {
		// Implement this also
		query = "select customerId, firstname, lastName, address, address2, contact, emailId, password "
				+ "from customer where emailId =?";
		Customer customer = null;
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, emailId);

			resulSet = preparedStatement.executeQuery();
			if (resulSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resulSet.getInt(1));
				customer.setFirstName(resulSet.getString(2));
				customer.setLastName(resulSet.getString(3));
				customer.setAddress(resulSet.getString(4));
				customer.setAddress2(resulSet.getString(5));
				customer.setContact(resulSet.getString(6));
				customer.setEmailId(resulSet.getString(7));
				customer.setPassword(resulSet.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != resulSet) {
				try {
					resulSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return customer;
	}

	@Override
	public List<Customer> viewCustomersByFirstName(String firstName) {
		// implement this also same as viewBooksByBookName
		query = "select customerId, firstname, lastName, address, address2, contact, emailId, password "
				+ "from customer where firstname like ?";
		List<Customer> customerList = new ArrayList<>();
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, "%" + firstName + "%");

			resulSet = preparedStatement.executeQuery();
			while (resulSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(resulSet.getInt(1));
				customer.setFirstName(resulSet.getString(2));
				customer.setLastName(resulSet.getString(3));
				customer.setAddress(resulSet.getString(4));
				customer.setAddress2(resulSet.getString(5));
				customer.setContact(resulSet.getString(6));
				customer.setEmailId(resulSet.getString(7));
				customer.setPassword(resulSet.getString(8));

				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != resulSet) {
				try {
					resulSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return customerList;
	}

}
