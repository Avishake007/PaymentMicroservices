package com.Payment.UserService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Payment.UserService.dto.UserRequestDto;
import com.Payment.UserService.dto.UserResponseDto;
import com.Payment.UserService.entities.User;
import com.Payment.UserService.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDto> addUser(@RequestBody @Valid UserRequestDto user){
		User addedUser = userService.addUser(user);
		
		UserResponseDto userResponseDto = new UserResponseDto(0, "Success", "User of id = "+addedUser.getUserId()+" is created", addedUser, null);
		
		  Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
		            .getUser(addedUser.getUserId())).withSelfRel();
		userResponseDto.add(selfLink);
		
		  Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
		            .deleteUserById(addedUser.getUserId())).withRel("delete");
		userResponseDto.add(deleteLink);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable String userId){
	
		User fetchedUser = userService.getUserById(userId); 
		
		UserResponseDto userResponseDto = new UserResponseDto(0, "Success", "Fetched user with userid = "+fetchedUser.getUserId(), fetchedUser, null);
		
		 Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
		            .getUsers()).withRel("getAll");
		userResponseDto.add(deleteLink);
		
		return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
	} 
	
	@GetMapping("/username/{username}")
	public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable("username") String username,HttpServletRequest request){
		User user = userService.getUserByUsername(username);
		
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(0, "Success","Found", user, null));
		
	}
	
	
	@GetMapping("/searchBy")
	public ResponseEntity<UserResponseDto> getUsersByFirstName(@RequestParam("firstName") String firstName){
		List<User> users = userService.getUserByFirstName(firstName);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(new UserResponseDto(0, "Success", users.size() +" users fetched", null, users));
		
	}
	
	@GetMapping("/sortBy")
	public ResponseEntity<UserResponseDto> getUsersOrderByLastName(@RequestParam("lastName") String lastName){
		List<User> users = userService.getUsersOrderByLastName(lastName);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(new UserResponseDto(0, "Success", users.size() +" users fetched", null, users));

		
	}
	 
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<UserResponseDto> getUsers(){
		List<User> users = userService.getAllUsers();
		
		return ResponseEntity.status(HttpStatus.FOUND).body(new UserResponseDto(0, "Success", users.size() +" users fetched", null, users));

		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserResponseDto> deleteUserById(@PathVariable String userId){
		userService.deleteUserById(userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponseDto(0, "Success", "Deleted user with user id = "+userId, null, null));
	}

	
	
	
}
