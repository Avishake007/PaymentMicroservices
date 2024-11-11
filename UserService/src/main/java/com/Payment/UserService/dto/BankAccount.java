package com.Payment.UserService.dto;



import com.Payment.UserService.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
	
	private String bankAccountNumber;
	@JsonIgnore
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
