package com.UpiPayment.PaymentService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.UpiPayment.PaymentService.dto.BankAccountRequest;
import com.UpiPayment.PaymentService.dto.BankAccountResponse;







@FeignClient(name="BANKACCOUNT")
public interface BankAccountService {
	
	@PutMapping("/bankAccount")
	public BankAccountResponse updateBankAccount(@RequestBody BankAccountRequest bankAccountRequest);
	
	@GetMapping("/bankAccount/{bankAccountId}")
	public BankAccountResponse getBankAccount(@PathVariable("bankAccountId") String bankAccountId);
	
	@GetMapping("/bankAccount/users/{userId}/primary")
	public BankAccountResponse fetchPrimaryBankAccountThroughUserId(@PathVariable("userId") String userId);

}
