package com.fluxcart.service;

import com.fluxcart.model.*;
import java.util.*;

public class OrderService {

	private List<Order> orders = new ArrayList<>();
	private int id = 1;

	public void placeOrder(String user, CartService cs, ProductService ps, PaymentService pay) {

		List<CartItem> cart = cs.getCart(user);
		if (cart.isEmpty()) {
			System.out.println("Cart empty!");
			return;
		}

		double total = 0;

		for (CartItem c : cart) {
			Product p = ps.get(c.productId);
			total += p.price * c.quantity;
		}

		if (total > 1000)
			total *= 0.9;

		Order order = new Order(id++, user, cart, total);

		boolean success = pay.process(total);

		if (!success) {
			for (CartItem c : cart) {
				ps.restoreStock(c.productId, c.quantity);
			}
			order.status = "FAILED";
			System.out.println("Order failed → rollback");
			return;
		}

		order.status = "PAID";
		orders.add(order);
		cs.clearCart(user);

		System.out.println("Order placed! ID: " + order.orderId);
	}

	public void viewOrders() {
		for (Order o : orders) {
			System.out.println("OrderID: " + o.orderId + " Status: " + o.status + " Total: " + o.total);
		}
	}
}