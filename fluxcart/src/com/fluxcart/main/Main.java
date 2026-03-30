package com.fluxcart.main;

import com.fluxcart.service.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ProductService ps = new ProductService();
		CartService cs = new CartService();
		OrderService os = new OrderService();
		PaymentService pay = new PaymentService();

		while (true) {
			System.out.println(
					"\n1.Add Product 2.View Products 3.Add to Cart 4.Remove from Cart 5.View Cart 6.Place Order 7.View Orders 0.Exit");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				System.out.print("ID Name Price Stock: ");
				ps.addProduct(sc.nextInt(), sc.next(), sc.nextDouble(), sc.nextInt());
				break;

			case 2:
				ps.viewProducts();
				break;

			case 3:
				System.out.print("User ProductID Qty: ");
				cs.addToCart(sc.next(), ps.get(sc.nextInt()), sc.nextInt());
				break;

			case 4:
				System.out.print("User ProductID: ");
				cs.removeFromCart(sc.next(), sc.nextInt(), ps);
				break;

			case 5:
				System.out.print("User: ");
				cs.viewCart(sc.next());
				break;

			case 6:
				System.out.print("User: ");
				os.placeOrder(sc.next(), cs, ps, pay);
				break;

			case 7:
				os.viewOrders();
				break;

			case 0:
				System.exit(0);
			}
		}
	}
}