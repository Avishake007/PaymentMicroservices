package com.UpiPayment.PaymentGateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UpiPayment.PaymentGateway.dto.UserLoginRequestDto;
import com.UpiPayment.PaymentGateway.dto.UserLoginResponseDto;
import com.UpiPayment.PaymentGateway.service.PaymentGatewayService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PaymentGatewayController {

	@Autowired
	PaymentGatewayService paymentGatewayService;
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest) {
		return paymentGatewayService.getCsrfToken(httpServletRequest);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
		System.out.println("PaymentGatewayController : "+userLoginRequestDto);
		
		String jwtToken = paymentGatewayService.verify(userLoginRequestDto);
		System.out.println("jwtToken : "+jwtToken);
		
		return (jwtToken != null) ? ResponseEntity.status(HttpStatus.OK).body(new UserLoginResponseDto(0,"Login Successful",jwtToken)):ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserLoginResponseDto(404,"Invalid username and/or password",null));
		
	}
	
	
}
