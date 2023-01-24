package com.book.dao;

import java.util.List;

import com.book.pojo.Customer;

public interface ICustomerDAO {
	/**
	 * Register/ Add new Customer into database table named Customer.
	 * 
	 * @param customer
	 *            customer object with all the information of a Customer
	 * @return true if customer added successfully in database, false otherwise
	 */
	boolean addCustomer(Customer customer);

	/**
	 * Update Existing Customer into database table named Customer.
	 * 
	 * @param customer
	 *            customer object with all the updated information of a Customer
	 * @return true if customer updated successfully in database, false otherwise
	 */
	boolean updateCustomer(Customer customer);

	/**
	 * Delete customer record from database table - Customer
	 * 
	 * @param customerId
	 *            find unique record associated with this customer id to delete it
	 * @return true if customer deleted from database, false otherwise
	 */
	boolean deleteCustomer(Integer customerId);

	/**
	 * @return list of Customers
	 */
	List<Customer> getAllCustomers();

	/**
	 * @param customerId
	 *            to search by customer id
	 * @return customer object
	 */
	Customer viewCustomerByCustomerId(Integer customerId);

	/**
	 * @param emailId
	 *            to search by email id
	 * @return customer object
	 */
	Customer viewCustomerByEmailId(String emailId);

	/**
	 * @param firstName
	 *            to search by first name
	 * @return list of customer objects
	 */
	List<Customer> viewCustomersByFirstName(String firstName);

}
