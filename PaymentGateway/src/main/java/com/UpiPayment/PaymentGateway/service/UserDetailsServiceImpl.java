package com.UpiPayment.PaymentGateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.UpiPayment.PaymentGateway.external.service.UserService;
import com.UpiPayment.PaymentGateway.models.User;
import com.UpiPayment.PaymentGateway.models.UserPrinciple;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username).getUser();
		
		return new  UserPrinciple(user);
	}

}
