package com.UpiPayment.BankAccount.exceptions;

public class DuplicateBankAccountNumberException extends RuntimeException{
	
	public DuplicateBankAccountNumberException(String message) {
		super(message);
	}

}
