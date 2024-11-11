package com.UpiPayment.PaymentGateway.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.UpiPayment.PaymentGateway.dto.UserResponseDto;

@FeignClient(name="USERSERVICE")
public interface UserService {
	
	@GetMapping("/users/username/{username}")
	public UserResponseDto getUserByUsername(@PathVariable("username") String username);
}
