package com.book.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.book.daoImpl.CustomerDAOImpl;
import com.book.pojo.Customer;

public class CustomerTest {

	public static void main(String[] args) {
		Integer customerId;
		String firstName;
		String lastName;
		String address;
		String address2;
		String contact;
		String emailId;
		String password;

		int choice;
		Customer customer = null;
		CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
		List<Customer> customerList = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("*******************************************");
			System.out.println("1) Add / Register Customer");
			System.out.println("2) Update Customer");
			System.out.println("3) Delete Customer");
			System.out.println("4) View Customer by Customer Id");
			System.out.println("5) View Customer by Customer Email");
			System.out.println("6) View Customer by Customer First Name");
			System.out.println("7) View All Customers ");
			System.out.println("8) Exit");
			System.out.println("*******************************************");
			System.out.println("Enter your choice: ");
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:// add new customer
				System.out.println("Enter first name : ");
				firstName = scanner.nextLine();
				System.out.println("Enter last name : ");
				lastName = scanner.nextLine();
				System.out.println("Enter address : ");
				address = scanner.nextLine();
				System.out.println("Enter contact : ");
				contact = scanner.nextLine();
				System.out.println("Enter email Id : ");
				emailId = scanner.nextLine();
				System.out.println("Enter password : ");
				password = scanner.nextLine();

				customer = new Customer();
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setAddress(address);
				customer.setContact(contact);
				customer.setEmailId(emailId);
				customer.setPassword(password);

				boolean insert = customerDAOImpl.addCustomer(customer);
				if (insert) {
					System.out.println("Customer added successfully!");
				} else {
					System.out.println("Error: in inserting customer record");
				}
				break;
			case 2: // update
				System.out.println("Enter customer id to search :");
				customerId = Integer.parseInt(scanner.nextLine());
				customer = customerDAOImpl.viewCustomerByCustomerId(customerId);
				if (null == customer) {
					System.out.println("No customer available with customerId-" + customerId);
				} else {
					System.out.println("Are you sure you want to update this customer? (Answer yes/no):");
					String ch = scanner.nextLine().toLowerCase();
					if ("yes".equals(ch)) {
						System.out.println("Enter first name : ");
						firstName = scanner.nextLine();

						System.out.println("Enter last name : ");
						lastName = scanner.nextLine();

						System.out.println("Enter address : ");
						address = scanner.nextLine();

						System.out.println("Enter address2 : ");
						address2 = scanner.nextLine();

						System.out.println("Enter contact : ");
						contact = scanner.nextLine();

						System.out.println("Enter email Id : ");
						emailId = scanner.nextLine();

						System.out.println("Enter password : ");
						password = scanner.nextLine();

						Customer customer1 = new Customer(customerId, firstName, lastName, address, address2, contact,
								emailId, password);
						boolean update = customerDAOImpl.updateCustomer(customer1);
						if (update) {
							System.out.println("Customer updated successfully!");
						} else {
							System.out.println("Error: in updating customer.");
						}
					} else {
						System.out.println("Thank You!!");
					}
				}
				break;
			case 3: // delete
				System.out.println("Enter customer id to search :");
				customerId = Integer.parseInt(scanner.nextLine());
				customer = customerDAOImpl.viewCustomerByCustomerId(customerId);
				if (null == customer) {
					System.out.println("No customer available with customerId-" + customerId);
				} else {
					System.out.println("Are you sure you want to remove it? (Answer yes/no):");
					String ch = scanner.nextLine().toLowerCase();
					if ("yes".equals(ch)) {
						boolean delete = customerDAOImpl.deleteCustomer(customerId);
						if (delete) {
							System.out.println("Customer deleted successfully!");
						} else {
							System.out.println("Error: in deleting customer.");
						}
					} else {
						System.out.println("Thank you!!");
					}
				}
				break;
			case 4: // view customer by customer id
				System.out.println("Enter customer id to search :");
				customerId = Integer.parseInt(scanner.nextLine());
				customer = customerDAOImpl.viewCustomerByCustomerId(customerId);
				if (null == customer) {
					System.out.println("No customer available with customerId-" + customerId);
				} else {
					System.out.println(customer);
				}
				break;
			case 5:// view customer by customer email
				System.out.println("Enter customer email id to search :");
				emailId = scanner.nextLine();
				customer = customerDAOImpl.viewCustomerByEmailId(emailId);
				if (null == customer) {
					System.out.println("No customer available with email id-" + emailId);
				} else {
					System.out.println(customer);
				}
				break;
			case 6: // view customer by customer first name
				System.out.println("Enter customer first name to search :");
				firstName = scanner.nextLine();
				customerList = customerDAOImpl.viewCustomersByFirstName(firstName);
				Iterator<Customer> iterator = customerList.iterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
				break;
			case 7: // view all customers
				customerList = customerDAOImpl.getAllCustomers();
				Iterator<Customer> iterator1 = customerList.iterator();
				while (iterator1.hasNext()) {
					System.out.println(iterator1.next());
				}
				break;

			case 8:
				System.exit(0);
				break;
			default:
				System.out.println("Please enter correct choice.");
			}

		} while (choice != 8);
		scanner.close();
	}

}
