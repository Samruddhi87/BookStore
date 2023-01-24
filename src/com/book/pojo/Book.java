package com.book.pojo;

public class Book {
	// instance variables /Fields/ states for a Book object
	private Integer bookId;
	private String bookName;
	private String description;
	private String author;
	private String publisher;
	private String category;
	private String isbn;
	private Double price;
	private Integer quantityOfAvailableBooks;
	private Double bookRating;
	private String bookImage;

	// Constructors to construct Book object
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Integer bookId, String bookName, String description, String author, String publisher, String category,
			String isbn, Double price, Integer quantityOfAvailableBooks, Double bookRating, String bookImage) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.description = description;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
		this.isbn = isbn;
		this.price = price;
		this.quantityOfAvailableBooks = quantityOfAvailableBooks;
		this.bookRating = bookRating;
		this.bookImage = bookImage;
	}

	// accessor and mutator methods
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
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName
	 *            the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher
	 *            the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the quantityOfAvailableBooks
	 */
	public Integer getQuantityOfAvailableBooks() {
		return quantityOfAvailableBooks;
	}

	/**
	 * @param quantityOfAvailableBooks
	 *            the quantityOfAvailableBooks to set
	 */
	public void setQuantityOfAvailableBooks(Integer quantityOfAvailableBooks) {
		this.quantityOfAvailableBooks = quantityOfAvailableBooks;
	}

	/**
	 * @return the bookRating
	 */
	public Double getBookRating() {
		return bookRating;
	}

	/**
	 * @param bookRating
	 *            the bookRating to set
	 */
	public void setBookRating(Double bookRating) {
		this.bookRating = bookRating;
	}

	/**
	 * @return the bookImage
	 */
	public String getBookImage() {
		return bookImage;
	}

	/**
	 * @param bookImage
	 *            the bookImage to set
	 */
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", description=" + description + ", author="
				+ author + ", publisher=" + publisher + ", category=" + category + ", isbn=" + isbn + ", price=" + price
				+ ", quantityOfAvailableBooks=" + quantityOfAvailableBooks + ", bookRating=" + bookRating
				+ ", bookImage=" + bookImage + "]";
	}

}
