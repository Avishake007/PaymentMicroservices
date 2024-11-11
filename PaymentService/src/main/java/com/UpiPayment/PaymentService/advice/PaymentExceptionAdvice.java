package com.UpiPayment.PaymentService.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.UpiPayment.PaymentService.dto.PaymentResponseDto;
import com.UpiPayment.PaymentService.exception.PaymentIdAlreadyCancelledException;
import com.UpiPayment.PaymentService.exception.PaymentIdNotFoundException;

@RestControllerAdvice
public class PaymentExceptionAdvice {
	
	@ExceptionHandler({PaymentIdNotFoundException.class})
	public ResponseEntity<PaymentResponseDto> handlePaymentNotFoundException(Exception e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PaymentResponseDto(404,"NOT FOUND",e.getMessage(),null));
	}
	
	@ExceptionHandler({PaymentIdAlreadyCancelledException.class})
	public ResponseEntity<PaymentResponseDto> handlePaymentIdAlreadyCancelledException(Exception e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PaymentResponseDto(400,"BAD REQUEST",e.getMessage(),null));
	}
	
	public ResponseEntity<PaymentResponseDto> handleUserServiceNotFoundException(Exception e){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new PaymentResponseDto(503,"SERVICE UNAVALIABLE",e.getMessage(),null));
	}
}
