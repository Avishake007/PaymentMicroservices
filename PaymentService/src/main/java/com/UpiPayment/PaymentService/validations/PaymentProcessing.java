package com.UpiPayment.PaymentService.validations;

import org.springframework.stereotype.Component;

import com.UpiPayment.PaymentService.dto.BankAccount;

@Component
public  abstract class PaymentProcessing {

	public abstract BankAccount validatePayerBankAccount(String bankAccountNumber); 
	public abstract boolean checkPayerBalance(BankAccount bankAccount, long balance);
	public abstract boolean validatePayeeUserId(String userId);
	public abstract BankAccount fetchPayeePrimaryBankAccount(String userId);
	public abstract void deductMoney(BankAccount payerBankAccount, BankAccount payeeBankAccount, long balance);
	
	public final String processPayment(String payerBankAccountNumber, String payeeUserId, long balance) {
		
		BankAccount payerBankAccount = validatePayerBankAccount(payerBankAccountNumber);
		
		if(checkPayerBalance(payerBankAccount, balance)) {
			if(validatePayeeUserId(payeeUserId)) {
				BankAccount payeeBankAccount = fetchPayeePrimaryBankAccount(payeeUserId);
				deductMoney(payerBankAccount, payeeBankAccount, balance);
				
			}
			else {
				return "Invalid Payee User Id. Please provide the correct userId";
			}
			
		}
		else {
			return "Insufficient Balance";
		}
		
		
		return "Payment Successful";
	}
	
	
	
}
