package com.Payment.UserService.entities;



import java.util.List;

import com.Payment.UserService.dto.BankAccount;
import com.Payment.UserService.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userId;
	@Column(unique = true)
	private String username;
	@NotBlank
	private String firstName;
	private String lastName;
	@Email
	private String emailId;
	@NotBlank
	private String password;
	private String role;
	private String createdDate;
	private UserStatus status;
	private String updatedDate;
	@Transient
	private List<BankAccount> bankAccountLi;
	
	
	
	
}
