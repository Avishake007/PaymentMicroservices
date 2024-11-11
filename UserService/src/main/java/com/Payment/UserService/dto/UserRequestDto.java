package com.Payment.UserService.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequestDto {
	@Column(unique = true)
	private String username;
	private String firstName;
	private String lastName;
	@Email
	private String emailId;
	@NotBlank
	@Size(min = 6,max=15)
	private String password;
	private List<BankAccountRequest> bankAccountLi = new ArrayList<>();
	
}
