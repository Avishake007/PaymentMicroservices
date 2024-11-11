package com.UpiPayment.PaymentGateway.models;


import com.UpiPayment.PaymentGateway.enums.AccountType;


public class BankAccount {
	private String bankAccountNumber;
	
	private String userId;
	private String bankName;
	private String bankBranch;
	private String bankIfscCode;
	private String upiPin;
	private AccountType accountType;
	private String isPrimary;
	private String createdDate;
	private String updatedDate;
}
