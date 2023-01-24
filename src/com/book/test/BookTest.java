package com.book.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.book.daoImpl.BookDAOImpl;
import com.book.pojo.Book;

public class BookTest {

	public static void main(String[] args) {
		Integer bookId;
		String bookName;
		String description;
		String author;
		String publisher;
		String category;
		String isbn;
		Double price;
		Integer quantityOfAvailableBooks;
		Double bookRating;
		String bookImage;

		Book book = null;
		BookDAOImpl bookDAOImpl = new BookDAOImpl();
		List<Book> bookList = new ArrayList<>();
		Set<String> set = null;

		int choice;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("*******************************************");
			System.out.println("Operations for Book : ");
			System.out.println("1) Add a Book ");
			System.out.println("2) Update a Book ");
			System.out.println("3) Delete a Book ");
			System.out.println("4) View Book by Book Id ");
			System.out.println("5) View All Books ");
			System.out.println("6) View All Books By Category ");
			System.out.println("7) View Book(s) By Book Name ");
			System.out.println("8) View Book(s) By Author ");
			System.out.println("9) View All Categories ");
			System.out.println("10) View All Authors ");
			System.out.println("11) Update Image ");
			System.out.println("12) Exit");
			System.out.println("*******************************************");
			System.out.println("Enter your choice : ");
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1: // add a book
				System.out.println("Enter book name :");
				bookName = scanner.nextLine().trim();
				System.out.println("Enter book description :");
				description = scanner.nextLine().trim();
				System.out.println("Enter book author name :");
				author = scanner.nextLine().trim();
				System.out.println("Enter book publisher name :");
				publisher = scanner.nextLine().trim();
				System.out.println("Enter book category (e.g. Fiction, Science) :");
				category = scanner.nextLine().trim();
				System.out.println("Enter book ISBN number :");
				isbn = scanner.nextLine().trim();
				System.out.println("Enter book price :");
				price = Double.parseDouble(scanner.nextLine().trim());
				System.out.println("Enter initial quantity of books  :");
				quantityOfAvailableBooks = Integer.parseInt(scanner.nextLine().trim());

				book = new Book();
				book.setBookName(bookName);
				book.setDescription(description);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setCategory(category);
				book.setIsbn(isbn);
				book.setPrice(price);
				book.setQuantityOfAvailableBooks(quantityOfAvailableBooks);

				System.out.println("Book deatils entered by user : \n" + book);
				boolean insert = bookDAOImpl.addBook(book);
				if (insert) { // insert == true
					System.out.println("Book added successfully!!");
				} else {
					System.out.println("Error: in inserting book into database");
				}
				break;

			case 2: // update book
				System.out.println("Enter Book Id to update : ");
				bookId = Integer.parseInt(scanner.nextLine());
				book = bookDAOImpl.viewBookByBookId(bookId);
				if (null == book) {
					System.out.println("Error: Book with BookId-" + bookId + " is not available.");
				} else {
					System.out.println("Book with BookId- " + bookId + " found.");
					System.out.println(book);
					System.out.println("----------------------------------------");
					System.out.println("Are you sure you want to update this book? (Answer in yes/no):");
					String input = scanner.nextLine().toLowerCase();
					if (input.equals("yes")) {
						System.out.println("Enter book name :");
						bookName = scanner.nextLine().trim();
						System.out.println("Enter book description :");
						description = scanner.nextLine().trim();
						System.out.println("Enter book author name :");
						author = scanner.nextLine().trim();
						System.out.println("Enter book publisher name :");
						publisher = scanner.nextLine().trim();
						System.out.println("Enter book category (e.g. Fiction, Science) :");
						category = scanner.nextLine().trim();
						System.out.println("Enter book ISBN number :");
						isbn = scanner.nextLine().trim();
						System.out.println("Enter book price :");
						price = Double.parseDouble(scanner.nextLine().trim());
						System.out.println("Enter initial quantity of books  :");
						quantityOfAvailableBooks = Integer.parseInt(scanner.nextLine().trim());
						System.out.println("Enter book rating  :");
						bookRating = Double.parseDouble(scanner.nextLine().trim());

						Book book1 = new Book();
						book1.setBookId(bookId);
						book1.setBookName(bookName);
						book1.setDescription(description);
						book1.setAuthor(author);
						book1.setPublisher(publisher);
						book1.setCategory(category);
						book1.setIsbn(isbn);
						book1.setPrice(price);
						book1.setQuantityOfAvailableBooks(quantityOfAvailableBooks);
						book1.setBookRating(bookRating);

						boolean update = bookDAOImpl.updateBook(book1);
						if (update) {
							System.out.println("Book with Id- " + book.getBookId() + " updated successfully!");
						} else {
							System.out.println("Error: in updating book into database");
						}
					} else {
						System.out.println("No Problem!");
					}
				}
				break;
			case 3: // delete book
				System.out.println("Enter book id to delete: ");
				bookId = Integer.parseInt(scanner.nextLine());
				book = bookDAOImpl.viewBookByBookId(bookId);
				if (null == book) {
					System.out.println("Book with bookId-" + bookId + " is not available");
				} else {
					System.out.println("Are you sure you want to delete this book? (Answer in yes/no):");
					String input = scanner.nextLine().toLowerCase();
					if (input.equals("yes")) {
						boolean delete = bookDAOImpl.deleteBook(bookId);
						if (delete) {
							System.out.println("Book with BookId-" + bookId + " is deleted successfully!");
						} else {
							System.out.println("Error: in deleting book.");
						}
					} else {
						System.out.println("Thank You!");
					}
				}
				break;
			case 4: // view book by book id
				System.out.println("Enter Book Id to search : ");
				bookId = Integer.parseInt(scanner.nextLine());
				book = bookDAOImpl.viewBookByBookId(bookId);
				if (null == book) {
					System.out.println("Error: Book with BookId-" + bookId + " is not available.");
				} else {
					System.out.println("Book with BookId- " + bookId + " found. Book Details are ->\n " + book);
				}
				break;
			case 5: // view all books
				bookList = bookDAOImpl.viewAllBooks();
				// for loop, enhanced for loop, iterator, forEach
				bookList.forEach(b -> System.out.println(b));
				break;
			case 6:// View All Books By Category
				System.out.println("Enter category to view books : ");
				category = scanner.nextLine().trim();
				bookList = bookDAOImpl.viewAllBooksByCategory(category);
				if (bookList.isEmpty()) {
					System.out.println("No books available for category - " + category);
				} else {
					for (Book b : bookList) {
						System.out.println(b);
					}
				}
				break;
			case 7: // View Book(s) By Book Name
				System.out.println("Enter book name to search:");
				bookName = scanner.nextLine().trim();
				bookList = bookDAOImpl.viewBooksByBookName(bookName);
				if (bookList.isEmpty()) {
					System.out.println("No books available by name - " + bookName);
				} else {
					for (Book b : bookList) {
						System.out.println(b);
					}
				}
				break;
			case 8: // view books by author
				System.out.println("Enter author name to search books : ");
				author = scanner.nextLine().trim();
				bookList = bookDAOImpl.viewBooksByAuthor(author);
				if (bookList.isEmpty()) {
					System.out.println("No books available by author - " + author);
				} else {
					for (Book b : bookList) {
						System.out.println(b);
					}
				}
				break;
			case 9: // View All Categories
				set = bookDAOImpl.getAllCategories();
				if (null == set || set.isEmpty()) {
					System.out.println("No categories found..");
				} else {
					set.forEach(c -> System.out.println(c));
				}
				break;
			case 10: // View All Authors
				set = bookDAOImpl.getAllAuthors();
				if (null == set || set.isEmpty()) {
					System.out.println("No authors found..");
				} else {
					set.forEach(c -> System.out.println(c));
				}
				break;
			case 11: // Update Image
				System.out.println("Enter book id to update image : ");
				bookId = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter image path to update image :  ");
				bookImage = scanner.nextLine();
				boolean update = bookDAOImpl.updateImage(bookImage, bookId);
				if (update) {
					System.out.println("Image upated successfully!");
				} else {
					System.out.println("Error : in updating image.");
				}
				break;
			case 12:
				System.exit(0);
				break;
			default:
				System.out.println("Enter correct choice.");
			}
		} while (choice != 12);

		scanner.close();
	}

}
