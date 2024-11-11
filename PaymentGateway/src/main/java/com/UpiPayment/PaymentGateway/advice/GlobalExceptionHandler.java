package com.UpiPayment.PaymentGateway.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.UpiPayment.PaymentGateway.dto.UserResponseDto;
import com.UpiPayment.PaymentGateway.exception.UserNotAuthorisedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotAuthorisedException.class)
	public ResponseEntity<UserResponseDto> handleUserNotAuthorisedException(Exception e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponseDto(401,"Unauthorized",e.getMessage(),null,null));
	}
}
