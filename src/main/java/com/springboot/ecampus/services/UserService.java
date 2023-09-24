package com.springboot.ecampus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.ecampus.entities.User;
import com.springboot.ecampus.exceptions.UserExistsException;
import com.springboot.ecampus.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException{
		if(userRepository.findByUsername(user.getUsername()) != null){
			throw new UserExistsException("user already exists");
		}
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
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		//if(!userRepository.findById(id).isPresent()) {
		if (!user.isPresent()) {
			throw new UserNotFoundException("User is not available in the repository");
			
		}
		return userRepository.findById(id);
	}
	
	
	//update user by id
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(id);
				if (!optionalUser.isPresent()) {
					throw new UserNotFoundException("User is not available in the repository ,provide the right uid");
					
				}
		
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserByid(Long id) throws UserNotFoundException {
		
	//if(!userRepository.findById(id).isPresent()) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User is not available in the repository ,provide the right uid for deletion");
	}
	
	userRepository.deleteById(id);
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	
}