package com.book.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.book.dao.IBookDAO;
import com.book.pojo.Book;
import com.book.util.DBConnectivity;

public class BookDAOImpl implements IBookDAO {

	@Override
	public boolean addBook(Book book) {
		String query = "insert into book (bookName, description,author,publisher,category,isbn,price,quantityOfAvailableBooks) "
				+ "values (?,?,?,?,?,?,?,?)";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			String bookName = book.getBookName();
			preparedStatement.setString(1, bookName);
			preparedStatement.setString(2, book.getDescription());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getPublisher());
			preparedStatement.setString(5, book.getCategory());
			preparedStatement.setString(6, book.getIsbn());
			preparedStatement.setDouble(7, book.getPrice());
			preparedStatement.setInt(8, book.getQuantityOfAvailableBooks());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Book> viewAllBooks() {
		List<Book> bookList = new ArrayList<>();
		ResultSet resultSet = null;
		Book book = null;
		String query = "Select bookId, bookName, description, author, publisher, category, isbn,price"
				+ ", quantityOfAvailableBooks, bookRating, bookImage from Book";

		try (Connection connection = DBConnectivity.makeConnection();
				Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setBookName(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setPublisher(resultSet.getString(5));
				book.setCategory(resultSet.getString(6));
				book.setIsbn(resultSet.getString(7));
				book.setPrice(resultSet.getDouble(8));
				book.setQuantityOfAvailableBooks(resultSet.getInt(9));
				book.setBookRating(resultSet.getDouble(10));
				book.setBookImage(resultSet.getString(11));

				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != resultSet) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bookList;
	}

	@Override
	public boolean updateBook(Book book) {
		String query = "Update Book set bookName=?, description=?,author=?,publisher =?,category=?,isbn=?"
				+ ",price =?, quantityOfAvailableBooks=?, bookRating=? where bookId=?";

		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, book.getBookName());
			preparedStatement.setString(2, book.getDescription());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getPublisher());
			preparedStatement.setString(5, book.getCategory());
			preparedStatement.setString(6, book.getIsbn());
			preparedStatement.setDouble(7, book.getPrice());
			preparedStatement.setInt(8, book.getQuantityOfAvailableBooks());
			preparedStatement.setDouble(9, book.getBookRating());
			preparedStatement.setInt(10, book.getBookId());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Book viewBookByBookId(Integer bookId) {
		Book book = null;
		ResultSet resultSet = null;
		String query = "Select bookId, bookName, description, author, publisher, category, isbn, price"
				+ ", quantityOfAvailableBooks, bookRating, bookImage from Book where bookId = " + bookId;// bookId=?
		try (Connection connection = DBConnectivity.makeConnection();
				Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setBookName(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setPublisher(resultSet.getString(5));
				book.setCategory(resultSet.getString(6));
				book.setIsbn(resultSet.getString(7));
				book.setPrice(resultSet.getDouble(8));
				book.setQuantityOfAvailableBooks(resultSet.getInt(9));
				book.setBookRating(resultSet.getDouble(10));
				book.setBookImage(resultSet.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	@Override
	public List<Book> viewAllBooksByCategory(String category) {
		ResultSet resultSet = null;
		Book book = null;
		List<Book> bookList = new ArrayList<>();
		String query = "Select bookId, bookName, description, author, publisher, category, isbn, price, "
				+ "	quantityOfAvailableBooks, bookRating, bookImage from book where category = ?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, category);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setBookName(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setPublisher(resultSet.getString(5));
				book.setCategory(resultSet.getString(6));
				book.setIsbn(resultSet.getString(7));
				book.setPrice(resultSet.getDouble(8));
				book.setQuantityOfAvailableBooks(resultSet.getInt(9));
				book.setBookRating(resultSet.getDouble(10));
				book.setBookImage(resultSet.getString(11));

				bookList.add(book);
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
		return bookList;
	}

	@Override
	public List<Book> viewBooksByBookName(String bookName) {
		ResultSet resultSet = null;
		Book book = null;
		List<Book> bookList = new ArrayList<>();
		String query = "select * from book where bookname like ? ";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, "%" + bookName + "%");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setBookName(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setPublisher(resultSet.getString(5));
				book.setCategory(resultSet.getString(6));
				book.setIsbn(resultSet.getString(7));
				book.setPrice(resultSet.getDouble(8));
				book.setQuantityOfAvailableBooks(resultSet.getInt(9));
				book.setBookRating(resultSet.getDouble(10));
				book.setBookImage(resultSet.getString(11));

				bookList.add(book);
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
		return bookList;
	}

	@Override
	public boolean deleteBook(Integer bookId) {
		String query = "Delete from book where bookId = ?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, bookId);

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Book> viewBooksByAuthor(String author) {
		ResultSet resultSet = null;
		Book book = null;
		List<Book> bookList = new ArrayList<>();
		String query = "select * from book where author like ? ";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, "%" + author + "%");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setBookName(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setPublisher(resultSet.getString(5));
				book.setCategory(resultSet.getString(6));
				book.setIsbn(resultSet.getString(7));
				book.setPrice(resultSet.getDouble(8));
				book.setQuantityOfAvailableBooks(resultSet.getInt(9));
				book.setBookRating(resultSet.getDouble(10));
				book.setBookImage(resultSet.getString(11));

				bookList.add(book);
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
		return bookList;
	}

	@Override
	public Set<String> getAllCategories() {
		String query = "select category from Book order by category";
		/*
		 * We are making TreeSet so that all duplicates are removed automatically and
		 * our categories will be sorted in alphabetical order.
		 */
		Set<String> categorySet = new TreeSet<String>();

		try (Connection conection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = conection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				categorySet.add(resultSet.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorySet;
	}

	@Override
	public Set<String> getAllAuthors() {
		String query = "select author from Book ";
		/*
		 * We are making TreeSet so that all duplicates are removed automatically and
		 * our authors will be sorted in alphabetical order.
		 */
		Set<String> authorSet = new TreeSet<String>();

		try (Connection conection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = conection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				authorSet.add(resultSet.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authorSet;
	}

	@Override
	public boolean updateImage(String bookImage, Integer bookId) {
		String query = "update book set bookImage=? where bookId=?";
		try (Connection connection = DBConnectivity.makeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, bookImage);
			preparedStatement.setInt(2, bookId);

			int n = preparedStatement.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
