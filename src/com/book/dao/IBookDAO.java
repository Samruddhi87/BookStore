package com.book.dao;

import java.util.List;
import java.util.Set;

import com.book.pojo.Book;

public interface IBookDAO {
	/**
	 * @param book
	 *            object will hold all the information of one book
	 * @return true if book added successfully in database, false otherwise
	 */
	boolean addBook(Book book);

	/**
	 * Updates Book
	 * 
	 * @param book
	 *            object will hold all the updated information of book
	 * @return true if book updated successfully in database, false otherwise
	 */
	boolean updateBook(Book book);

	/**
	 * Delete Book
	 * 
	 * @param bookId
	 * @return true if book gets deleted, false otherwise
	 */
	boolean deleteBook(Integer bookId);

	/**
	 * Retrieving all books from database table named Book
	 * 
	 * @return list of books retrieved from database
	 */
	List<Book> viewAllBooks();

	/**
	 * This method is used to retrieve book details from book table for provided
	 * book id
	 * 
	 * @param bookId
	 * @return book details wrapped inside Book object, null otherwise
	 */
	Book viewBookByBookId(Integer bookId);

	/**
	 * Retrieving all books of given category from database table named Book
	 * 
	 * @param category
	 *            to search books by category
	 * @return list of books retrieved from database, empty list otherwise
	 */
	List<Book> viewAllBooksByCategory(String category);

	/**
	 * Retrieving all books matching with provided bookName from database table
	 * named Book
	 * 
	 * @param bookName
	 *            to search books by book name
	 * @return list of books retrieved from database, empty list otherwise
	 */
	List<Book> viewBooksByBookName(String bookName);

	/**
	 * Retrieving all books matching with provided author name from database table
	 * named Book
	 * 
	 * @param author
	 *            to search books by author name
	 * @return list of books retrieved from database, empty list otherwise
	 */
	List<Book> viewBooksByAuthor(String author);

	/**
	 * Retrieving all categories from database table named Book
	 * 
	 * @return set of unique categories from database
	 */
	Set<String> getAllCategories();

	/**
	 * Retrieving all author names from database table named Book
	 * 
	 * @return set of unique authors from database
	 */
	Set<String> getAllAuthors();

	/**
	 * Update a book image path identified by book id
	 * 
	 * @param bookImage
	 *            path of image which to be stored
	 * @param bookId
	 *            id of book for which image path should be updated
	 * @return true if updated successfully, false otherwise
	 */
	boolean updateImage(String bookImage, Integer bookId);
}
