package com.book.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.IOrderDAO;
import com.book.pojo.Cart;
import com.book.pojo.Order;
import com.book.util.DBConnectivity;

public class OrderDAOImpl implements IOrderDAO {

	String query = "";

	@Override
	public Order placeOrder(Order order) {
		query = "insert into ordertbl (emailId, customerId, orderDate, status, ShippingAddress, totalCost) "
				+ "values (?,?,?,?,?,?)";
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Order newOrder = null;
		try {
			connection = DBConnectivity.makeConnection();
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, order.getEmailId());
			preparedStatement.setInt(2, order.getCustomerId());
			preparedStatement.setString(3, order.getOrderDate());
			preparedStatement.setString(4, order.getStatus());
			preparedStatement.setString(5, order.getShippingAddress());
			preparedStatement.setDouble(6, order.getTotalCost());

			int n = preparedStatement.executeUpdate();
			if (n > 0) {
				CartDAOImpl cartDAOImpl = new CartDAOImpl();

				// retrieving cart items by emailId
				List<Cart> Cart = cartDAOImpl.showMyCart(order.getEmailId());

				// clearing cart after retrieving for placing order
				cartDAOImpl.clearCart(order.getEmailId());

				/*
				 * .getGeneratedKeys : method returns auto generated keys, in our case
				 * auto_increment field i.e. primary key value of recently inserted record;
				 * 
				 * it returns values in resultSet
				 */
				resultSet = preparedStatement.getGeneratedKeys();

				if (resultSet.next()) {
					/*
					 * retrieving orderId field in resultSet as an output of getGeneratedKeys()
					 */
					int orderId = resultSet.getInt(1);
					/*
					 * copying all cart items into orderDetail table by calling this method and we
					 * need to set order id for those cart items, so we are passing order id and
					 * cart list as parameters to this method
					 */
					copyCartIntoOrderDetails(Cart, orderId);
				}
				preparedStatement.close();
				resultSet.close();

				// retrieve recently inserted record
				query = "select * from ordertbl where emailid=? and orderDate =?";
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setString(1, order.getEmailId());
				preparedStatement.setString(2, order.getOrderDate());

				resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					newOrder = new Order();
					newOrder.setOrderId(resultSet.getInt(1));
					newOrder.setEmailId(resultSet.getString("emailId"));
					newOrder.setCustomerId(resultSet.getInt("customerId"));
					newOrder.setOrderDate(resultSet.getString("orderDate"));
					newOrder.setShippingAddress(resultSet.getString("shippingAddress"));
					newOrder.setStatus(resultSet.getString("status"));
					newOrder.setTotalCost(resultSet.getDouble("totalCost"));

					return newOrder;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != resultSet) {
					resultSet.close();
				}
				if (null != preparedStatement) {
					preparedStatement.close();
				}
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean copyCartIntoOrderDetails(List<Cart> Cart, Integer orderId) {
		query = "insert into orderDetails (cartId , bookId, quantity, orderId) values (?,?,?,?)";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			int n = 0;
			for (Cart cart2 : Cart) {
				preparedStatement.setInt(1, cart2.getCartId());
				preparedStatement.setInt(2, cart2.getBookId());
				preparedStatement.setInt(3, cart2.getQuantity());
				preparedStatement.setInt(4, orderId);

				n += preparedStatement.executeUpdate();
			}
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
	public boolean cancelOrder(Integer orderId) {
		/*
		 * query = "update ordertbl set status =? where orderId =?"; try (Connection
		 * connection = DBConnectivity.makeConnection(); PreparedStatement
		 * preparedStatement = connection.prepareStatement(query)) {
		 * 
		 * preparedStatement.setString(1, "Cancelled"); preparedStatement.setInt(2,
		 * orderId);
		 * 
		 * int n = preparedStatement.executeUpdate(); if (n > 0) { return true; } }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		return updateStatus(orderId, "Cancelled");
	}

	@Override
	public boolean updateStatus(Integer orderId, String status) {
		// TODO Auto-generated method stub
		query = "update ordertbl set status =? where orderId =?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, orderId);

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
	public List<Order> viewOrderDetailsByOrderId(Integer orderId) {
		query = "select * from orderDetails where orderId=?";
		ResultSet resultSet = null;
		List<Order> orderDetails = new ArrayList<>();
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, orderId);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				Order order = new Order();
				order.setBookId(resultSet.getInt("bookId"));
				order.setOrderId(resultSet.getInt("orderId"));
				order.setQuantity(resultSet.getInt("quantity"));
				order.setCartId(resultSet.getInt("cartId"));

				orderDetails.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderDetails;
	}

	@Override
	public List<Order> viewMyOrderHistory(String emailId) {
		query = "select * from ordertbl where emailid=?";
		ResultSet resultSet = null;
		List<Order> orders = new ArrayList<>();

		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, emailId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderId"));
				// implement other details
				order.setEmailId(resultSet.getString("emailId"));
				order.setCustomerId(resultSet.getInt("customerId"));
				order.setOrderDate(resultSet.getString("orderDate"));
				order.setStatus(resultSet.getString("status"));
				order.setShippingAddress(resultSet.getString("shippingAddress"));
				order.setTotalCost(resultSet.getDouble("totalCost"));

				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != resultSet) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return orders;
	}

	@Override
	public List<Order> viewAllOrders() {
		query = "select * from ordertbl";
		ResultSet resultSet = null;
		List<Order> orders = new ArrayList<>();
		try (Connection connection = DBConnectivity.makeConnection();
				Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderId"));
				order.setEmailId(resultSet.getString("emailId"));
				order.setCustomerId(resultSet.getInt("customerId"));
				order.setOrderDate(resultSet.getString("orderDate"));
				order.setStatus(resultSet.getString("status"));
				order.setShippingAddress(resultSet.getString("shippingAddress"));
				order.setTotalCost(resultSet.getDouble("totalCost"));

				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != resultSet) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return orders;
	}

}
