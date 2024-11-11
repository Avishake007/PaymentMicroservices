package com.Payment.UserService.advise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Payment.UserService.dto.UserResponseDto;
import com.Payment.UserService.exceptions.DuplicateBankAccountNumberException;
import com.Payment.UserService.exceptions.UserNotFoundException;

import feign.FeignException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<UserResponseDto> notFoundExceptionHandler(Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponseDto(404, "Not Found", e.getMessage(), null, null));
	}
	
	@ExceptionHandler({FeignException.class})
	public ResponseEntity<UserResponseDto> feignExceptionHandler(Exception e) {
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseDto(402, "BAD REQUEST", e.getMessage(), null, null));
	}
	
}
