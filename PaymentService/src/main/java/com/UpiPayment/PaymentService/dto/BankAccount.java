package com.UpiPayment.PaymentService.dto;

import com.UpiPayment.PaymentService.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
	
	private String bankAccountNumber;
	private String userId;
	private String bankName;
	private String bankBranch;
	private String bankIfscCode;
	private String upiPin;
	private AccountType accountType;
	private long balance;
	private String isPrimary;
	private String createdDate;
	private String updatedDate;
	
}
