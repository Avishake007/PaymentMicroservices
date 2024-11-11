package com.Payment.UserService.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Payment.UserService.dto.BankAccount;
import com.Payment.UserService.dto.BankAccountRequest;
import com.Payment.UserService.dto.UserRequestDto;
import com.Payment.UserService.entities.User;
import com.Payment.UserService.enums.UserStatus;
import com.Payment.UserService.exceptions.UserNotAuthorisedException;
import com.Payment.UserService.exceptions.UserNotFoundException;
import com.Payment.UserService.external.services.BankAccountService;
import com.Payment.UserService.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BankAccountService bankAccountService;
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	JWTService jwtService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	
	@Transactional
	public User addUser(UserRequestDto user) {
		
		String currDate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
		
		
		
		User addeduser = new User(null,user.getUsername(), user.getFirstName(),user.getLastName(),user.getEmailId(),user.getPassword(),"USER",currDate,UserStatus.ACTIVE,currDate,null);
		addeduser.setPassword(encoder.encode(addeduser.getPassword()));
		
		
		User createdUser =userRepository.save(addeduser);
		System.out.println("UserService : "+createdUser.getUserId());
		List<BankAccount> bankAccountLi = new ArrayList<>();
		for(BankAccountRequest bankAccount : user.getBankAccountLi()) {
			bankAccount.setUserId(createdUser.getUserId());
			System.out.println("BankAccountRequest : "+bankAccount.getUserId());
			//bankAccountService.createBankAccount(bankAccount);
			 bankAccountLi.add(bankAccountService.createBankAccount(bankAccount).getBankAccount());
		}
		
		createdUser.setBankAccountLi(bankAccountLi);
		
		return createdUser;
	}
	
	public User getUserById(String id) {
		
		User user = userRepository.findById(id).orElseThrow(() ->new UserNotFoundException("User with user id = "+id+" is not present"));
		
		//System.out.println(bankAccountService.getBankAccountsByUserId(id));
		List<BankAccount> bankAccountLi = bankAccountService.getBankAccountsByUserId(id).getBankAccountLi();
		user.setBankAccountLi(bankAccountLi);
		
		return user;
				
	}
	
	public User getUserByUsername(String username) {
		
		String authHeader = request.getHeader("Authorization");
		String token = "";
		String currUsername = username;
		
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            currUsername = jwtService.extractUserName(token);
        }
		System.out.println("currUsername : "+currUsername);
		
		if(!currUsername.equals(username) ) {
			throw new UserNotAuthorisedException("User can only fetch its on details");
		}
		
		return userRepository.findUserByUsername(username);
	}
	
	public List<User> setBankAccountsToUser(List<User> users){
		for(User user : users) {
			List<BankAccount> bankAccountLi = bankAccountService.getBankAccountsByUserId(user.getUserId()).getBankAccountLi();
			user.setBankAccountLi(bankAccountLi);
		}
		return users;
	}
	
	public List<User> getUsersOrderByLastName(String lastName) {
		List<User> users =  userRepository.findByLastNameOrderByLastName(lastName);
		users = setBankAccountsToUser(users);
		return users;
				
	}
	
	public List<User> getUserByFirstName(String firstName) {
		List<User> users = userRepository.findByFirstName(firstName);
		users = setBankAccountsToUser(users);
		return users;
				
	}
	
	public List<User> getAllUsers(){
		List<User> users = userRepository.findAll();
		
		users = setBankAccountsToUser(users);
		return users;
		
	}
	
	public void deleteUserById(String userId) {
		
		getUserById(userId);
		
		bankAccountService.deleteByUserId(userId);
		
		userRepository.deleteById(userId);
		
	}
	
	
}
