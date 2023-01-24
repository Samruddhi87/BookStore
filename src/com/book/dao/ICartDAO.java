package com.book.dao;

import java.util.List;

import com.book.pojo.Cart;

public interface ICartDAO {

	/**
	 * This method is used to add/insert item into cart i.e. into database table
	 * named Cart
	 * 
	 * @param cart
	 *            Cart object which stores all information of each cart item
	 * @return true if cart item added into database table successfully, false
	 *         otherwise
	 */
	boolean addToCart(Cart cart);

	/**
	 * This method is used to update quantity of cart item identified by cartId from
	 * Cart table in database
	 * 
	 * @param cartId
	 *            to search cart record into table named Cart
	 * @param quantity
	 *            quantity to be updated instead of current quantity
	 * @return true if cart item's quantity updated into database table
	 *         successfully, false otherwise
	 */
	boolean updateQuantity(Integer cartId, Integer quantity);

	/**
	 * This method is used to remove/delete cart item from Cart table
	 * 
	 * @param cartId
	 *            to search cart record into table named Cart
	 * @return true if cart item deleted from database table successfully, false
	 *         otherwise
	 */
	boolean removeCartItem(Integer cartId);

	/**
	 * This method is used to remove/delete all records in Cart table which are
	 * identified by provided emailId
	 * 
	 * @param emailId
	 *            to search cart records of this customer into table named Cart
	 * @return true if all cart items deleted successfully, false otherwise
	 */
	boolean clearCart(String emailId);

	/**
	 * This method returns cart object which stores all information of cart searched
	 * by using cartId from database table named Cart
	 * 
	 * @param cartId
	 *            to search cart record into table named Cart
	 * @return cart object
	 */
	Cart viewCartByCartId(Integer cartId);

	/**
	 * This method returns list of cart objects which stores all information of cart
	 * searched by using emailId from database table named Cart
	 * 
	 * @param emailId
	 *            to search cart records of this customer into table named Cart
	 * @return list of cart items available in table of this emailId
	 */
	List<Cart> showMyCart(String emailId);
}
