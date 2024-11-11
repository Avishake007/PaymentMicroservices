package com.UpiPayment.BankAccount.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.UpiPayment.BankAccount.entities.BankAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountResponse extends RepresentationModel<BankAccountResponse>{

	private long errorCode;
	private String errorDescription;
	private String message;
	private BankAccount bankAccount;
	private List<BankAccount> bankAccountLi;
}
