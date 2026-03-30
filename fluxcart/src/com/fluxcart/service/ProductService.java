package com.fluxcart.service;

import com.fluxcart.model.Product;
import java.util.*;

public class ProductService {

	private Map<Integer, Product> products = new HashMap<>();

	public void addProduct(int id, String name, double price, int stock) {
		if (products.containsKey(id)) {
			System.out.println("Duplicate Product ID!");
			return;
		}
		products.put(id, new Product(id, name, price, stock));
		System.out.println("Product added!");
	}

	public void viewProducts() {
		for (Product p : products.values()) {
			System.out.println(p.id + " | " + p.name + " | ₹" + p.price + " | Stock: " + p.stock);
		}
	}

	public Product get(int id) {
		return products.get(id);
	}

	public void restoreStock(int id, int qty) {
		Product p = products.get(id);
		if (p != null)
			p.stock += qty;
	}
}