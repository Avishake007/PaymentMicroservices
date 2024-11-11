package com.UpiPayment.BankAccount.entities;


import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.UpiPayment.BankAccount.enums.AccountType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class BankAccount implements Serializable{
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.UUID)
	//private String bankAccountId;
	
	@Id
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
