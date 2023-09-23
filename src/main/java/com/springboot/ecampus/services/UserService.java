package com.springboot.ecampus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecampus.entities.User;
import com.springboot.ecampus.repositories.UserRepository;

@Service
public class UserService {
	
	//Auto wire the userReppository
	@Autowired
	private UserRepository userRepository;
	
	//getallUsers Method
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
		}
	
	//createUsers 
	public User createUser(User user) {
		
		return userRepository.save(user);
	}
	
	/*
	 * //getUsers by id 
	 * public Optional<User> getUserById(Long id) { 
	 * Optional<User> user = userRepository.findById(id);
	 * 
	 * return user; 
	 * }
	 */
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	
	//update user by id
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserByid(Long id) {
	if(userRepository.findById(id).isPresent()) {
		userRepository.deleteById(id);
	}
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	
}