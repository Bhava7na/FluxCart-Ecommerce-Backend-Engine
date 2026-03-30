package com.fluxcart.service;

import java.util.Random;

public class PaymentService {

	Random rand = new Random();

	public boolean process(double amount) {
		boolean success = rand.nextBoolean();
		System.out.println(success ? "Payment Success" : "Payment Failed");
		return success;
	}
}
