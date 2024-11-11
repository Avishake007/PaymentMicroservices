package com.UpiPayment.PaymentGateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UpiPayment.PaymentGateway.dto.UserLoginResponseDto;
import com.UpiPayment.PaymentGateway.dto.UserResponseDto;
import com.UpiPayment.PaymentGateway.exception.UserNotAuthorisedException;
import com.UpiPayment.PaymentGateway.external.service.UserService;
import com.UpiPayment.PaymentGateway.service.JWTService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	JWTService jwtService;
	
	
	@GetMapping("/username/{username}")
	public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable("username") String username, HttpServletRequest request){
		String authHeader = request.getHeader("Authorization");
        String token = null;
        String currUsername = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            currUsername = jwtService.extractUserName(token);
        }
        
        UserResponseDto userResponseDto;
        
        if(currUsername.equals(username)) {
        	userResponseDto = userService.getUserByUsername(username);
        }
        else {	
        	throw new UserNotAuthorisedException("You are not allowed to perform this operation. You can only use your own username");
        }
        
        return ResponseEntity.status(HttpStatus.FOUND).body(userResponseDto);
        
	}
}
