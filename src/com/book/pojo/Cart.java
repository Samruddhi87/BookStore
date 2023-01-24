package com.book.pojo;

public class Cart {
	private Integer cartId;
	private Integer bookId;
	private Integer quantity;
	private String emailId;
	private Integer customerId;
	private Book book; // one cart may contain multiple books- i.e. One to many relationship

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Integer cartId, Integer bookId, Integer quantity, String emailId, Integer customerId, Book book) {
		super();
		this.cartId = cartId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.emailId = emailId;
		this.customerId = customerId;
		this.book = book;
	}

	/**
	 * @return the cartId
	 */
	public Integer getCartId() {
		return cartId;
	}

	/**
	 * @param cartId
	 *            the cartId to set
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 *            the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book
	 *            the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", bookId=" + bookId + ", quantity=" + quantity + ", emailId=" + emailId
				+ ", customerId=" + customerId + ", book=" + book + "]";
	}

}
