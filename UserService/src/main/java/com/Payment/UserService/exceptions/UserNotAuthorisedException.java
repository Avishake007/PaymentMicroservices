package com.Payment.UserService.exceptions;

@SuppressWarnings("serial")
public class UserNotAuthorisedException extends RuntimeException{
	
	public UserNotAuthorisedException(String message) {
		super(message);
	}

}
