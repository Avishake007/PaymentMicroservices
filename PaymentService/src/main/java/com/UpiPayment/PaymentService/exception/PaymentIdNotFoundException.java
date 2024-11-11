package com.UpiPayment.PaymentService.exception;

public class PaymentIdNotFoundException extends RuntimeException{
	public PaymentIdNotFoundException(String message) {
		super(message);
	}
}
