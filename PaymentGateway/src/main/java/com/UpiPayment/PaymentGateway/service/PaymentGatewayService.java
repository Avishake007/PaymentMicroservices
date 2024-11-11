package com.UpiPayment.PaymentGateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;

import com.UpiPayment.PaymentGateway.dto.UserLoginRequestDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PaymentGatewayService {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWTService jwtService;

	
	public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest ) {
		return (CsrfToken) httpServletRequest.getAttribute("_csrf");
	}
	
	public String verify(UserLoginRequestDto userLoginRequestDto) {
		
		Authentication authentication = null;
		
		System.out.println("Payment : "+userLoginRequestDto.getUsername() +" "+ userLoginRequestDto.getPassword());
		try {
		 authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUsername(), userLoginRequestDto.getPassword()));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		System.out.println("authentication : "+authentication);
		
			
		if(authentication.isAuthenticated()) {
			
			return jwtService.generateToken(userLoginRequestDto.getUsername());
		}
		
		return null;
	}
}
