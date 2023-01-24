package com.book.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.book.daoImpl.BookDAOImpl;
import com.book.daoImpl.CartDAOImpl;
import com.book.pojo.Book;
import com.book.pojo.Cart;

public class CartTest {

	public static void main(String[] args) {
		Integer cartId;
		Integer bookId;
		Integer quantity;
		String emailId;
		Integer customerId;

		Book book = null;
		Cart cart = null;

		BookDAOImpl bookDAOImpl = new BookDAOImpl();
		CartDAOImpl cartDAOImpl = new CartDAOImpl();

		int choice;
		List<Cart> cartList = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("*******************************************");
			System.out.println("1) Add to Cart");
			System.out.println("2) Remove Cart Item");
			System.out.println("3) View Cart by Cart Id");
			System.out.println("4) View My Cart");
			System.out.println("5) Update Quantity");
			System.out.println("6) Clear cart");
			System.out.println("7) Exit");
			System.out.println("*******************************************");
			System.out.println("Enter your choice: ");
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:// Add to Cart
				System.out.println("Enter book id to add into cart : ");
				bookId = Integer.parseInt(scanner.nextLine());

				System.out.println("Enter quantity of book :");
				quantity = Integer.parseInt(scanner.nextLine());

				System.out.println("Enter email id : ");
				emailId = scanner.nextLine();

				System.out.println("Enter customer id : ");
				customerId = Integer.parseInt(scanner.nextLine());
				// cart object
				cart = new Cart();
				cart.setBookId(bookId);
				cart.setQuantity(quantity);
				cart.setEmailId(emailId);
				cart.setCustomerId(customerId);

				book = bookDAOImpl.viewBookByBookId(bookId);
				cart.setBook(book);

				boolean insert = cartDAOImpl.addToCart(cart);
				if (insert) {
					System.out.println("Book added to cart successfully!");
				} else {
					System.out.println("Error: in adding book to cart.");
				}
				break;
			case 2: // remove Cart item
				System.out.println("Enter cart id to remove item from cart : ");
				cartId = Integer.parseInt(scanner.nextLine());
				cart = cartDAOImpl.viewCartByCartId(cartId);
				if (null == cart) {
					System.out.println("No cart available with id-" + cartId);
				} else {
					System.out.println("Are you sure you want to remove it? (Answer yes/no):");
					String ch = scanner.nextLine().toLowerCase();
					if ("yes".equals(ch)) {
						boolean remove = cartDAOImpl.removeCartItem(cartId);
						if (remove) {
							System.out.println("Cart with Id-" + cartId + " is removed successfully!");
						} else {
							System.out.println("Error: while removing item from cart.");
						}
					} else {
						System.out.println("Thank You!!");
					}
				}
				break;
			case 3: // view cart by cart id
				System.out.println("Enter cart id to search: ");
				cartId = Integer.parseInt(scanner.nextLine());
				cart = cartDAOImpl.viewCartByCartId(cartId);
				if (null == cart) {
					System.out.println("No cart available with id-" + cartId);
				} else {
					System.out.println(cart);
				}
				break;
			case 4: // show my cart
				System.out.println("Enter email id to view cart : ");
				emailId = scanner.nextLine();
				cartList = cartDAOImpl.showMyCart(emailId);
				if (cartList.isEmpty()) {
					System.out.println("No cart found for user emailid =" + emailId);
				} else {
					// iterate over list
					for (Cart c : cartList) {
						System.out.println(c);
					}
				}
				break;
			case 5: // update quantity
				System.out.println("Enter cart id to update qunatity : ");
				cartId = Integer.parseInt(scanner.nextLine());

				System.out.println("Enter updated quantity : ");
				quantity = Integer.parseInt(scanner.nextLine());

				boolean update = cartDAOImpl.updateQuantity(cartId, quantity);
				if (update) {
					System.out.println("Quantity  updated successfully!");
				} else {
					System.out.println("Error: in updating quantity");
				}
				break;
			case 6: // clear cart
				System.out.println("Enter email id to clear cart : ");
				emailId = scanner.nextLine();

				if (cartDAOImpl.clearCart(emailId)) {
					System.out.println("Cart cleared..");
				} else {
					System.out.println("Error: in clearing cart.");
				}
				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.out.println("Enter correct choice.");
			}
		} while (choice != 7);
		scanner.close();
	}

}
