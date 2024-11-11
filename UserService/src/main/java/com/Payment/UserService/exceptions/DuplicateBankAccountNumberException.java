package com.Payment.UserService.exceptions;

public class DuplicateBankAccountNumberException extends RuntimeException{
	public DuplicateBankAccountNumberException(String message) {
		super(message);
	}
}
