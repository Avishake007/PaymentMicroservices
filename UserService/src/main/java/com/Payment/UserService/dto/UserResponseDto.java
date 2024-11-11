package com.Payment.UserService.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.Payment.UserService.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto extends RepresentationModel<UserResponseDto>{
	private long errorCode;
	private String errorDescription;
	private String message;
	private User user;
	private List<User> users;
	
	
}
