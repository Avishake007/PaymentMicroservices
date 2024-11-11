package com.UpiPayment.PaymentService.exception;

public class PaymentIdAlreadyCancelledException extends RuntimeException{
	
	public PaymentIdAlreadyCancelledException(String message) {
		super(message);
	}
}
