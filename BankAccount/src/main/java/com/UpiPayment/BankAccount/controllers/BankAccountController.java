package com.UpiPayment.BankAccount.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UpiPayment.BankAccount.dto.BankAccountRequest;
import com.UpiPayment.BankAccount.dto.BankAccountResponse;
import com.UpiPayment.BankAccount.entities.BankAccount;
import com.UpiPayment.BankAccount.service.BankAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {
	
	@Autowired
	BankAccountService service;
	
	@PostMapping
	public ResponseEntity<BankAccountResponse> createBankAccount(@RequestBody @Valid BankAccountRequest bankAccountRequest){
		System.out.println("BankAccountRequest"+bankAccountRequest);
		BankAccount currBankAccount =  service.createBankAccount(bankAccountRequest);
		BankAccountResponse bankAccountResponse = new BankAccountResponse(0, "SUCCESS", "A bank account is created", currBankAccount, null);
		  Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BankAccountController.class)
		            .getBankAccount(currBankAccount.getBankAccountNumber())).withSelfRel();
		bankAccountResponse.add(selfLink);
		
		
		Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BankAccountController.class)
	            .deleteBankAccount(currBankAccount.getBankAccountNumber())).withRel("delete");
		bankAccountResponse.add(deleteLink);
		
		return ResponseEntity.status(HttpStatus.OK).body(bankAccountResponse);
		
	}
	
	@GetMapping("/{bankAccountId}")
	public ResponseEntity<BankAccountResponse> getBankAccount(@PathVariable("bankAccountId") String bankAccountId){
		
		BankAccount currBankAccount =  service.getBankAccount(bankAccountId);
		BankAccountResponse bankAccountResponse = new BankAccountResponse(0, "SUCCESS", "Found bank account with id : "+bankAccountId, currBankAccount, null);

		 Link getAllBankAccountsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BankAccountController.class)
		            .getAllBankAccounts()).withRel("getAll");
			bankAccountResponse.add(getAllBankAccountsLink);
		
		return ResponseEntity.status(HttpStatus.OK).body(bankAccountResponse);
		
	}
	
	@GetMapping
	public ResponseEntity<BankAccountResponse> getAllBankAccounts(){
		
		List<BankAccount> bankAccountLi =  service.getAllBankAccount();
		
		
		
		
		return ResponseEntity.status(HttpStatus.FOUND).body(new BankAccountResponse(0, "SUCCESS", bankAccountLi.size()+" bank account(s) found", null, bankAccountLi));
		
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<BankAccountResponse> getBankAccountsByUserId(@PathVariable("userId") String userId){
		
		List<BankAccount> bankAccountLi =  service.getBankAccountsByUserId(userId);
		
		BankAccountResponse bankAccountResponse = new BankAccountResponse(0, "SUCCESS", bankAccountLi.size()+" bank account(s) found", null, bankAccountLi);
		
		Link getAllBankAccountsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BankAccountController.class)
	            .fetchPrimaryBankAccountThroughUserId(userId)).withRel("getAll");
		bankAccountResponse.add(getAllBankAccountsLink);
		
		return ResponseEntity.status(HttpStatus.OK).body(bankAccountResponse);
		
	}
	
	@GetMapping("/users/{userId}/primary")
	public ResponseEntity<BankAccountResponse> fetchPrimaryBankAccountThroughUserId(@PathVariable("userId") String userId){
		
		BankAccount primaryBankAccount =  service.getPrimaryBankAccountThroughUserId(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new BankAccountResponse(0, "SUCCESS", "Found Primary bank account with user id : "+userId, primaryBankAccount, null));
		
	}
	
	@PutMapping
	public ResponseEntity<BankAccountResponse> updateBankAccount(@RequestBody BankAccountRequest bankAccountRequest){
		
		BankAccount currBankAccount =  service.updateBankAccount(bankAccountRequest);
		
		return ResponseEntity.status(HttpStatus.OK).body(new BankAccountResponse(0, "SUCCESS", "Bank Account updated successfully", currBankAccount, null));
		
	}
	

	@DeleteMapping("/{bankAccountId}")
	public ResponseEntity<BankAccountResponse> deleteBankAccount(@PathVariable("bankAccountId") String bankAccountId){
		
		 service.deleteBankAccountById(bankAccountId);
		 BankAccountResponse bankAccountResponse = new BankAccountResponse(0, "SUCCESS", "Bank Account deleted", null, null);
		 Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BankAccountController.class)
		            .deleteAllBankAccount()).withRel("delete");
			bankAccountResponse.add(deleteLink);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bankAccountResponse);
		
	}
	
	@DeleteMapping
	public ResponseEntity<BankAccountResponse> deleteAllBankAccount(){
		
		 service.deleteAllBankAccount();
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new BankAccountResponse(0, "SUCCESS", "All Bank Accounts deleted", null, null));
		
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<BankAccountResponse> deleteByUserId(@PathVariable("userId") String userId){
		
		 service.deleteByUserId(userId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new BankAccountResponse(0, "SUCCESS", "Bank Accounts with "+userId+" are deleted", null, null));
		
	}
	
	
}
