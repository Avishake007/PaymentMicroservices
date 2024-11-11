package com.UpiPayment.PaymentService.dto;

import com.UpiPayment.PaymentService.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
private String userId;
	
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String createdDate;
	private UserStatus status;
	private String updatedDate;
}
