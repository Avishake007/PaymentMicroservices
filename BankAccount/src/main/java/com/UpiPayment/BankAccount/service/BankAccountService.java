package com.UpiPayment.BankAccount.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.UpiPayment.BankAccount.constant.KafkaConstant;
import com.UpiPayment.BankAccount.dto.BankAccountRequest;
import com.UpiPayment.BankAccount.entities.BankAccount;
import com.UpiPayment.BankAccount.exceptions.BankAccountNotFound;
import com.UpiPayment.BankAccount.exceptions.DuplicateBankAccountNumberException;
import com.UpiPayment.BankAccount.repository.BankAccountRepository;

import jakarta.transaction.Transactional;

@Service
public class BankAccountService implements BankAccountServiceIF{
	
	@Autowired
	BankAccountRepository repository; 
	
	@Override
	public BankAccount createBankAccount(BankAccountRequest bankAccountRequest) {
		
		try {
			getBankAccount(bankAccountRequest.getBankAccountNumber());
			throw new DuplicateBankAccountNumberException("Bank Account Number = "+bankAccountRequest.getBankAccountNumber()+ " is already present");
		}
		catch(BankAccountNotFound e) {
			
		}
		
		int cnt = getBankAccountsByUserId(bankAccountRequest.getUserId()).size();
		
		String isPrimary = "False";
		
		if(cnt == 0)
			isPrimary  = "True";
		
		String currDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		BankAccount bankAccount = new BankAccount( bankAccountRequest.getBankAccountNumber(), bankAccountRequest.getUserId(), bankAccountRequest.getBankName(), bankAccountRequest.getBankBranch(), bankAccountRequest.getBankIfscCode(), bankAccountRequest.getUpiPin(), bankAccountRequest.getAccountType(), bankAccountRequest.getBalance(),isPrimary, currDate, currDate);
		System.out.println("User Id : "+bankAccountRequest.getUserId());
		
		repository.save(bankAccount);
		
		return bankAccount;
	}
	
	@Override
	@Cacheable(key="#bankAccountId", value="bankAccount")
	public BankAccount getBankAccount(String bankAccountId) {
		BankAccount bankAccount = repository.findById(bankAccountId).orElseThrow(() -> new BankAccountNotFound("Bank Account Id = "+ bankAccountId+" is not present"));
		
		return bankAccount;
	}
	
	@Override
	@Cacheable(value="bankAccount")
	public List<BankAccount> getAllBankAccount() {
		List<BankAccount> bankAccount = repository.findAll();
		
		return bankAccount;
	}
	
	@Override
	public List<BankAccount> getBankAccountsByUserId(String userId) {
		List<BankAccount> bankAccount = repository.findByUserId(userId);
		
		return bankAccount;
	}
	
	public BankAccount getPrimaryBankAccountThroughUserId(String userId) {
		BankAccount primaryBankAccount = repository.getPrimaryBankAccount(userId);
		return primaryBankAccount;
	}
	
		
	public BankAccount updateBankAccount(BankAccountRequest bankAccountRequest){
		
		String currDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		BankAccount prevBankAccount = getBankAccount(bankAccountRequest.getBankAccountNumber());
		BankAccount bankAccount = new BankAccount( bankAccountRequest.getBankAccountNumber(), bankAccountRequest.getUserId(), bankAccountRequest.getBankName(), bankAccountRequest.getBankBranch(), bankAccountRequest.getBankIfscCode(), bankAccountRequest.getUpiPin(), bankAccountRequest.getAccountType(), bankAccountRequest.getBalance(),prevBankAccount.getIsPrimary(), prevBankAccount.getCreatedDate(), currDate);

		return repository.save(bankAccount);
	}
	

	@Override
	@Caching(evict = 
					{ @CacheEvict( key="#bankAccountId", value="bankAccount"),
					  @CacheEvict(  value="bankAccount" , allEntries = true)
					}
	)
			
	
	public void deleteBankAccountById(String bankAccountId) {
		repository.deleteById(bankAccountId);
		
	}
	
	@Override
	public void deleteAllBankAccount() {
		repository.deleteAll();
		
	}
	
	@Override
	@Transactional
	public void deleteByUserId(String userId) {
		repository.deleteByUserId(userId);
	}
}
