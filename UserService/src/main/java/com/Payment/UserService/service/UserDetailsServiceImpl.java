package com.Payment.UserService.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Payment.UserService.dto.UserPrinciple;
import com.Payment.UserService.entities.User;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		
		return new  UserPrinciple(user);
	}

}
