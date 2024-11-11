package com.UpiPayment.PaymentGateway.models;

import java.util.List;

import com.UpiPayment.PaymentGateway.enums.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String userId;
	
	private String username;
	
	private String firstName;
	private String lastName;
	
	private String emailId;
	
	private String password;
	private String role;
	private String createdDate;
	private UserStatus status;
	private String updatedDate;
	private List<BankAccount> bankAccountLi;
}
