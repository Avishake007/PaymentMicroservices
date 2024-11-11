package com.UpiPayment.PaymentGateway.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;


import com.UpiPayment.PaymentGateway.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto extends RepresentationModel<UserResponseDto>{
	private long errorCode;
	private String errorDescription;
	private String message;
	private User user;
	private List<User> users;
}
