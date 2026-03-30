package com.fluxcart.model;

import java.util.List;

public class Order {
	public int orderId;
	public String userId;
	public List<CartItem> items;
	public double total;
	public String status;

	public Order(int orderId, String userId, List<CartItem> items, double total) {
		this.orderId = orderId;
		this.userId = userId;
		this.items = items;
		this.total = total;
		this.status = "CREATED";
	}
}