package com.Payment.UserService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Payment.UserService.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	List<User> findByFirstName(String firstName);
	List<User> findByLastNameOrderByLastName(String lastname);
	User findUserByUsername(String username);
	
}
