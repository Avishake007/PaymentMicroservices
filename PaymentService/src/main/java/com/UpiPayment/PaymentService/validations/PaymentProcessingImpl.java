package com.UpiPayment.PaymentService.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.UpiPayment.PaymentService.dto.BankAccount;
import com.UpiPayment.PaymentService.dto.BankAccountRequest;
import com.UpiPayment.PaymentService.external.services.BankAccountService;
import com.UpiPayment.PaymentService.external.services.UserService;

@Component
public class PaymentProcessingImpl extends PaymentProcessing{
	
	@Autowired
	BankAccountService service;
	
	@Autowired
	UserService userService;

	@Override
	public BankAccount validatePayerBankAccount(String bankAccountNumber) {
		System.out.println("validatePayerBankAccount");
		return service.getBankAccount(bankAccountNumber).getBankAccount();
	}

	@Override
	public boolean checkPayerBalance(BankAccount bankAccount, long balance) {
		System.out.println("Check");
		if(bankAccount.getBalance() >= balance) {
			return true;
		}
		return false;
	}

	@Override
	public boolean validatePayeeUserId(String userId) {
		System.out.println("validatePayeeUserId");
		try {
			userService.getUser(userId);
		}
		catch(Exception e) {
			System.out.println("Going inside exception for validate Payee User");
			return false;
		}
		return true;
	}

	@Override
	public BankAccount fetchPayeePrimaryBankAccount(String userId) {
		System.out.println("fetchPayeePrimaryBankAccount");
		return service.fetchPrimaryBankAccountThroughUserId(userId).getBankAccount();
	}

	@Override
	public void deductMoney(BankAccount payerBankAccount, BankAccount payeeBankAccount, long balance) {
		System.out.println("deductMoney");
		payerBankAccount.setBalance(payerBankAccount.getBalance() - balance);
		payeeBankAccount.setBalance(payeeBankAccount.getBalance() + balance);
		
		BankAccountRequest payerBankAccountRequest = new BankAccountRequest(payerBankAccount.getBankAccountNumber(),payerBankAccount.getUserId(),payerBankAccount.getBankName(),payerBankAccount.getBankBranch(),payerBankAccount.getBankIfscCode(),payerBankAccount.getUpiPin(),payerBankAccount.getAccountType(),payerBankAccount.getBalance());
		BankAccountRequest payeeBankAccountRequest = new BankAccountRequest(payeeBankAccount.getBankAccountNumber(),payeeBankAccount.getUserId(),payeeBankAccount.getBankName(),payeeBankAccount.getBankBranch(),payeeBankAccount.getBankIfscCode(),payeeBankAccount.getUpiPin(),payeeBankAccount.getAccountType(),payeeBankAccount.getBalance());

		service.updateBankAccount(payerBankAccountRequest);
		service.updateBankAccount(payeeBankAccountRequest);
		
		
	}

}
