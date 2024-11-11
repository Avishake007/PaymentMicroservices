package com.UpiPayment.BankAccount.service;

import java.util.List;

import com.UpiPayment.BankAccount.dto.BankAccountRequest;
import com.UpiPayment.BankAccount.dto.BankAccountResponse;
import com.UpiPayment.BankAccount.entities.BankAccount;

public interface BankAccountServiceIF {
	
	 BankAccount createBankAccount(BankAccountRequest bankAccountRequest);
	 BankAccount getBankAccount(String bankAccountId);
	 List<BankAccount> getAllBankAccount();
	 BankAccount updateBankAccount(BankAccountRequest bankAccountRequest);
	 void deleteBankAccountById(String bankAccountId);
	 void deleteAllBankAccount();
	 List<BankAccount> getBankAccountsByUserId(String userId);
	 void deleteByUserId(String userId);
	
	 
	

}
