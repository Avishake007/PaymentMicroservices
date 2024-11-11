package com.UpiPayment.PaymentService.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
	
	private long errorCode;
	private String errorDescription;
	private String message;
	private User user;
	private List<User> users;
}
