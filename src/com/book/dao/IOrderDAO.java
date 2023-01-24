package com.book.dao;

import java.util.List;

import com.book.pojo.Order;

public interface IOrderDAO {
	/**
	 * @param order
	 * @return
	 */
	Order placeOrder(Order order);

	/**
	 * @param orderId
	 * @return
	 */
	boolean cancelOrder(Integer orderId);

	/**
	 * @param orderId
	 * @param status
	 * @return
	 */
	boolean updateStatus(Integer orderId, String status);

	/**
	 * @param orderId
	 * @return
	 */
	List<Order> viewOrderDetailsByOrderId(Integer orderId);

	/**
	 * @param cemailId
	 * @return
	 */
	List<Order> viewMyOrderHistory(String emailId);

	/**
	 * @return
	 */
	List<Order> viewAllOrders();
}
