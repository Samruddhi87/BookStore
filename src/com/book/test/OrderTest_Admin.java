package com.book.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.book.daoImpl.OrderDAOImpl;
import com.book.pojo.Order;

public class OrderTest_Admin {

	public static void main(String[] args) {
		Integer orderId;
		String orderDate;
		String status; // shipped, placed, delivered
		String shippingAddress;
		Double totalCost;

		int choice;
		List<Order> orderList = new ArrayList<>();
		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("****************************************");
			System.out.println("1)--------- Update Status");
			System.out.println("2)--------- View All Orders");
			System.out.println("3)--------- Exit");
			System.out.println("****************************************");
			System.out.println("Enter your choice :");
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1: // update status
				System.out.println("Enter order id to update its status :");
				orderId = Integer.parseInt(scanner.nextLine());

				System.out.println("Enter status to be updated for orderId-" + orderId);
				status = scanner.nextLine();

				if (orderDAOImpl.updateStatus(orderId, status)) {
					System.out.println("Status Updated successfully for orderId-" + orderId);
				} else {
					System.out.println("Error: while updating status of orderId-" + orderId);
				}
				break;
			case 2: // view all orders
				orderList = orderDAOImpl.viewAllOrders();
				if (orderList.isEmpty()) {
					System.out.println("No orders to display..");
				} else {
					for (Order o : orderList) {
						System.out.println("Order Id => " + o.getOrderId());
						System.out.println("Customer Email Id => " + o.getEmailId());
						System.out.println("Ordered on Date => " + o.getOrderDate());
						System.out.println("Status of Order => " + o.getStatus());
						System.out.println("Delivery Location => " + o.getShippingAddress());
						System.out.println("Total Bill => " + o.getTotalCost());
						System.out.println("---------------------------------------------------------------");
					}
				}
				break;
			case 3:
				// exit
				System.exit(0);
				break;
			default:
				System.out.println("Enter correct choice");
			}
		} while (choice != 3);
		scanner.close();
	}

}
