package com.Payment.UserService.external.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Payment.UserService.dto.BankAccountRequest;
import com.Payment.UserService.dto.BankAccountResponse;
import com.Payment.UserService.dto.UserResponseDto;

import feign.Headers;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@FeignClient(name = "BANKACCOUNT", url = "http://localhost:8083")
public interface BankAccountService {
	
	Logger LOGGER = LoggerFactory.getLogger(BankAccountService.class);
	
	@GetMapping("/bankAccount/users/{userId}")
	@RateLimiter(name="userBankAccountRateLimiter")
	@Retry(name ="userBankAccountRetry")
	@CircuitBreaker(name="userBankAccountBreaker", fallbackMethod = "userBankAccountFallback")
	public BankAccountResponse getBankAccountsByUserId(@PathVariable("userId") String userId);
	
	@PostMapping("/bankAccount")
	public BankAccountResponse createBankAccount(@RequestBody BankAccountRequest bankAccountRequest);

	@DeleteMapping("/bankAccount/users/{userId}")
	public ResponseEntity<BankAccountResponse> deleteByUserId(@PathVariable("userId") String userId);
	
	default  BankAccountResponse userBankAccountFallback(@PathVariable("userId") String userId, Throwable e){
		LOGGER.error("Bank Account Server is unavailable ");
		throw new RuntimeException("Down");
	}
}
