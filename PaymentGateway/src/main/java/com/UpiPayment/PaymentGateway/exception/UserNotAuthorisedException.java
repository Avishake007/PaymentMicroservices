package com.UpiPayment.PaymentGateway.exception;

@SuppressWarnings("serial")
public class UserNotAuthorisedException extends RuntimeException{
	
	public UserNotAuthorisedException(String message) {
		super(message);
	}

}
