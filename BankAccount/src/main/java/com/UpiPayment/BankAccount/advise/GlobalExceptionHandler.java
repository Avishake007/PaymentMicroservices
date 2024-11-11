package com.UpiPayment.BankAccount.advise;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.UpiPayment.BankAccount.dto.BankAccountResponse;
import com.UpiPayment.BankAccount.exceptions.BankAccountNotFound;
import com.UpiPayment.BankAccount.exceptions.DuplicateBankAccountNumberException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BankAccountNotFound.class)
	public ResponseEntity<BankAccountResponse> handleBankNotFoundException(Exception e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BankAccountResponse(404, "NOT FOUND", e.getMessage(), null, null));
	}
	
	@ExceptionHandler(DuplicateBankAccountNumberException.class)
	public ResponseEntity<BankAccountResponse> handleDuplicateBankAccountNumberException(Exception e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new BankAccountResponse(409, "BANK ACCOUNT NUMBER DUPLICATED", e.getMessage(), null, null));
	}
	
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<BankAccountResponse> handleTransactionSystemException(ConstraintViolationException e){
		
		Map<String,List<String>> errorMap = new HashMap<>();
		 Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		 
	        for(ConstraintViolation<?> constraintViolation : constraintViolations) {
	        	
	        	String key = constraintViolation.getPropertyPath().toString();
	        	if(errorMap.containsKey(key) == true) {
		        	List<String> errorMsgList = errorMap.get(key);
		        	errorMsgList.add(constraintViolation.getMessage());
		        	errorMap.put(constraintViolation.getPropertyPath().toString(), errorMsgList);
	        	}
	        	else {
	        		List<String> errorMsgList= new LinkedList<>();
	        		errorMsgList.add(constraintViolation.getMessage());
		        	errorMap.put(constraintViolation.getPropertyPath().toString(), errorMsgList);
	        		
	        	}
	        	
	        }
	        
	        String message = "{";
	        
	        for(String key : errorMap.keySet()) {
	        	message += key+ ":" + errorMap.get(key)+" ";
	        }
	        message += "}";
	        
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new BankAccountResponse(403, "FORBIDDEN", message, null, null));
	}
	
}
