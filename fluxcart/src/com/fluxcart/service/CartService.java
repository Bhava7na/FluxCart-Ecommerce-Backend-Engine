package com.fluxcart.service;

import com.fluxcart.model.*;
import java.util.*;

public class CartService {

	private Map<String, List<CartItem>> carts = new HashMap<>();

	public void addToCart(String user, Product p, int qty) {
		if (p == null || p.stock < qty) {
			System.out.println("Stock not available!");
			return;
		}

		synchronized (p) {
			p.stock -= qty;
		}

		carts.putIfAbsent(user, new ArrayList<>());
		carts.get(user).add(new CartItem(p.id, qty));

		System.out.println("Added to cart!");
	}

	public void removeFromCart(String user, int productId, ProductService ps) {
		List<CartItem> cart = carts.get(user);
		if (cart == null)
			return;

		Iterator<CartItem> it = cart.iterator();
		while (it.hasNext()) {
			CartItem c = it.next();
			if (c.productId == productId) {
				ps.restoreStock(productId, c.quantity);
				it.remove();
				System.out.println("Removed from cart");
			}
		}
	}

	public List<CartItem> getCart(String user) {
		return carts.getOrDefault(user, new ArrayList<>());
	}

	public void viewCart(String user) {
		List<CartItem> cart = getCart(user);
		if (cart.isEmpty()) {
			System.out.println("Cart empty");
			return;
		}
		for (CartItem c : cart) {
			System.out.println("Product: " + c.productId + " Qty: " + c.quantity);
		}
	}

	public void clearCart(String user) {
		carts.remove(user);
	}
}