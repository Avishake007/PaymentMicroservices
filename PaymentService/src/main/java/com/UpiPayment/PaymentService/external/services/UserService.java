package com.UpiPayment.PaymentService.external.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.UpiPayment.PaymentService.dto.UserResponseDto;
import com.UpiPayment.PaymentService.exception.UserServiceNotFoundException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@FeignClient(name = "USERSERVICE", url = "http://localhost:8082")
public interface UserService {
	
	public static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	
	//@Retry(name = "paymentUserRetry")
	@GetMapping("/users/{userId}")
	@CircuitBreaker(name ="paymentUserBreaker",fallbackMethod = "paymentUserFallback")
	 UserResponseDto getUser(@PathVariable("userId") String userId);
	
	
	default  UserResponseDto paymentUserFallback(@PathVariable("userId") String userId, Throwable e) {
		LOGGER.error("Currently the user service is unavaiblable");
		throw new UserServiceNotFoundException("User Service is currently down. Please try again later");
	}
}
