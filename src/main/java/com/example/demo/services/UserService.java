package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExist;
import com.example.demo.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExist {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if(existingUser!=null)
		{
			
			throw new UserExist("User ALready Exist in REPO");
	}
		return userRepository.save(user);

}
	
	//get USer by ID
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User not found in repo");
		}
		return user;
	}
	
	
	//updateUserByID
	
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		
		if(!optionalUser.isPresent())
		{
			throw new UserNotFoundException("User not id found in repo to update");
		}

		user.setId(id);
		return userRepository.save(user);}

	
	
// Delete USer By ID

	public void deleteUSerById(Long id)  {
Optional<User> optionalUser = userRepository.findById(id);
		
		
		if(!optionalUser.isPresent())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User-id not found in repo to update");
		}		
		userRepository.deleteById(id);
	}
	
	
	public User getUserByName(String username) {
		return userRepository.findByUsername(username);
		
	}
	
	
	
	
	
	
	
}
