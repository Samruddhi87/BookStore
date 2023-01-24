package com.book.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.ICartDAO;
import com.book.pojo.Cart;
import com.book.util.DBConnectivity;

public class CartDAOImpl implements ICartDAO {
	String query = "";
	ResultSet resultSet = null;

	@Override
	public boolean addToCart(Cart cart) {
		query = "insert into cart (bookId, quantity, emailId, customerId) values (?, ?, ? ,?)";

		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, cart.getBookId());
			preparedStatement.setInt(2, cart.getQuantity());
			preparedStatement.setString(3, cart.getEmailId());
			preparedStatement.setInt(4, cart.getCustomerId());

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
	public boolean removeCartItem(Integer cartId) {
		query = "delete from cart where cartid =?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, cartId);
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
	public Cart viewCartByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		query = "select cartid, bookid, quantity, emailid, customerid from cart where cartid = ?";
		Cart cart = null;
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, cartId);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cart = new Cart();
				cart.setCartId(resultSet.getInt(1));

				int bookid = resultSet.getInt(2);
				cart.setBookId(bookid);

				cart.setQuantity(resultSet.getInt(3));
				cart.setEmailId(resultSet.getString(4));
				cart.setCustomerId(resultSet.getInt(5));

				// get the book object from viewBookByBookId() method of bookDAOImpl and set to
				// cart
				BookDAOImpl bookDAOImpl = new BookDAOImpl();
				cart.setBook(bookDAOImpl.viewBookByBookId(bookid));
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

		return cart;
	}

	@Override
	public List<Cart> showMyCart(String emailId) {
		// TODO Auto-generated method stub
		query = "select cartid, bookid, quantity, emailid, customerid from cart where emailid=?";
		List<Cart> cartList = new ArrayList<Cart>();
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, emailId);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Cart cart = new Cart();
				// implement set data from resultSet to Cart object
				cart.setCartId(resultSet.getInt(1));

				int bookid = resultSet.getInt(2);
				cart.setBookId(bookid);

				cart.setQuantity(resultSet.getInt(3));
				cart.setEmailId(resultSet.getString(4));
				cart.setCustomerId(resultSet.getInt(5));

				// get the book object from viewBookByBookId() method of bookDAOImpl and set to
				// cart
				BookDAOImpl bookDAOImpl = new BookDAOImpl();
				cart.setBook(bookDAOImpl.viewBookByBookId(bookid));
				cartList.add(cart);
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
		return cartList;
	}

	@Override
	public boolean updateQuantity(Integer cartId, Integer quantity) {
		// TODO Auto-generated method stub
		query = "Update Cart set quantity=? where cartId = ?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, cartId);

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
	public boolean clearCart(String emailId) {
		// TODO Auto-generated method stub
		query = "delete from cart where emailId=?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, emailId);

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

}
