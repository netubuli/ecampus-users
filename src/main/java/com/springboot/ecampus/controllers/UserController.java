package com.springboot.ecampus.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.ecampus.entities.User;
import com.springboot.ecampus.exceptions.UserExistsException;
import com.springboot.ecampus.exceptions.UserNotFoundException;
import com.springboot.ecampus.services.UserService;

@RestController
public class UserController {
	
	//Auto wire the userService
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		
		return userService.getAllUsers();	
		}
	
	//createUser Method
	//@postmapping annotation
	//@requestbody annotation
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) throws UserExistsException {
		  
		return userService.createUser(user);
	}
	
	//get user by id
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			// TODO Auto-generated catch block
			//e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	//update user by id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
		userService.deleteUserByid(id);
	}
	
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
		
	}

}
