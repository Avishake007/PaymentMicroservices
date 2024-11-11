package com.Payment.UserService.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountResponse {

	private long errorCode;
	private String errorDescription;
	private String message;
	private BankAccount bankAccount;
	private List<BankAccount> bankAccountLi;
}

