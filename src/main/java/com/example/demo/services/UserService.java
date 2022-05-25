package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//get all user Methdod
	
	
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
		}
	// create user method
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		
		return user;
	}
	
	
	//updateUserByID
	
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	
	
	
// Delete USer By ID
	
	public void deleteUSerById(Long id) {
		if (userRepository.findById(id).isPresent())
		{
			userRepository.deleteById(id);
		}
		
	}
	
	public User getUserByName(String username) {
		return userRepository.findByUsername(username);
		
	}
	
	
	
	
	
	
	
}
