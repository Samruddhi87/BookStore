package com.book.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.book.daoImpl.CartDAOImpl;
import com.book.daoImpl.CustomerDAOImpl;
import com.book.daoImpl.LoginDAOImpl;
import com.book.daoImpl.OrderDAOImpl;
import com.book.pojo.Cart;
import com.book.pojo.Order;

public class OrderTest_Customer {

	public static void main(String[] args) {
		Integer orderId;
		String orderDate;
		String status; // shipped, placed, delivered
		String shippingAddress;
		Double totalCost;
		String emailId;
		String password;

		int choice;
		List<Order> orderList = new ArrayList<>();

		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
		LoginDAOImpl loginDAOImpl = new LoginDAOImpl();
		CartDAOImpl cartDAOImpl = new CartDAOImpl();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please Login...");

		System.out.println("Enter EmailId: ");
		emailId = scanner.nextLine();

		System.out.println("Enter Password: ");
		password = scanner.nextLine();

		boolean login = loginDAOImpl.validateLogin(emailId, password);

		if (login) {

			System.out.println("Login successful, You can now place the order for Books in cart..");

			List<Cart> cart = cartDAOImpl.showMyCart(emailId); // we are trying to retrieve cart of logged in customer
			if (null == cart || cart.isEmpty()) {
				System.out.println("Your cart is empty. Please add books to your cart first, before placeing order.");

				do {
					System.out.println("****************************************");

					System.out.println("2)--------- Cancel Order");
					System.out.println("3)--------- View Order History");
					System.out.println("4)--------- Exit");
					System.out.println("****************************************");
					System.out.println("Enter your choice :");
					choice = Integer.parseInt(scanner.nextLine());

					switch (choice) {
					case 2: // cancel order
						System.out.println("Enter order id to cancel the order: ");
						orderId = Integer.parseInt(scanner.nextLine());
						if (orderDAOImpl.cancelOrder(orderId)) {
							System.out.println("Your order cancelled successfully!");
						} else {
							System.out.println("Error in cancelling order..");
						}
						break;
					case 3: // view order history
						orderList = orderDAOImpl.viewMyOrderHistory(emailId);
						if (null == orderList || orderList.isEmpty()) {
							System.out.println("No orders");
						} else {

							// iterate over orderList
						}

						break;
					case 4:
						System.exit(0);
						break;
					default:
						System.out.println("Enter correct choice!");
					}
				} while (choice != 4);
			} else {
				System.out.println("****************** Your Cart Details *******************");
				double cartTotal = 0;
				for (Cart c : cart) {
					System.out.println("Cart Id : " + c.getCartId());
					System.out.println("Book Id : " + c.getBookId());
					System.out.println("Book Name : " + c.getBook().getBookName());
					System.out.println("Quantity : " + c.getQuantity());
					System.out.println("Book Unit Price : " + c.getBook().getPrice());
					double netPrice = c.getBook().getPrice() * c.getQuantity();
					System.out.println("Net Price : " + netPrice);
					cartTotal += netPrice;
					System.out.println("-----------------------------------------------------------");
				}

				System.out.println("Total amount payable =" + cartTotal);
				System.out.println("===========================================");

				System.out.println("1)--------- Place Order\n Enter choice: ");
				choice = Integer.parseInt(scanner.nextLine());
				if (choice == 1) {
					System.out.println("Confirm the delivery address for the order :");
					shippingAddress = scanner.nextLine();

					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					orderDate = LocalDateTime.now().format(format);

					status = "Processing";

					Order order = new Order();
					order.setEmailId(emailId);
					order.setCustomerId(new CustomerDAOImpl().viewCustomerByEmailId(emailId).getCustomerId());
					order.setOrderDate(orderDate);
					order.setStatus(status);
					order.setShippingAddress(shippingAddress);
					order.setTotalCost(cartTotal);

					Order placedOrder = orderDAOImpl.placeOrder(order);
					if (null != placedOrder) {
						System.out.println("Your order has been placed successfully. Thank you for shopping with us.");
						System.out.println("********************************************");
						System.out.println("Order No : " + placedOrder.getOrderId());
						System.out.println("Total Amount Paid : " + placedOrder.getTotalCost());
						System.out.println("Shipping Address : " + placedOrder.getShippingAddress());

						LocalDate date = LocalDate.parse(placedOrder.getOrderDate(), format);
						LocalDate deliveryDate = date.plusDays(8);

						format = DateTimeFormatter.ofPattern("dd -LLLL- yyyy");
						String d = deliveryDate.format(format);
						System.out.println("Your order will be delivered before - " + d);
					} else {
						System.out.println("Sorry something went wrong your order could not be placed.");
					}
				}

			}

		} else {
			System.out.println("Error in Login :: Email Id or Password is wrong.");
		}
		scanner.close();
	}

}
