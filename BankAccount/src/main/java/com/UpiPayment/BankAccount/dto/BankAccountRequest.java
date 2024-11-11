package com.UpiPayment.BankAccount.dto;

import com.UpiPayment.BankAccount.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountRequest {
	private String bankAccountNumber;
	private String userId;
	private String bankName;
	private String bankBranch;
	private String bankIfscCode;
	private String upiPin;
	private AccountType accountType;
	private long balance;
	
}
