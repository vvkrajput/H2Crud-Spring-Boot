package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExist;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	
@GetMapping("/users")	
	//get All users Method--
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}

//Create Use

@PostMapping("/users")
     public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder){
	try {
    	 userService.createUser(user);
    	 HttpHeaders headers = new HttpHeaders();
    	 headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
    	 return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}catch(UserExist exx)
	{
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exx.getMessage());
	}
 }

//getUSerByID

@GetMapping("/users/{id}")
public Optional<User>getUserById(@PathVariable("id") Long id){
	try {
		return userService.getUserById(id);

	}
	
	catch(UserNotFoundException ex){
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
}
	

//UpdateuserById


@PutMapping("user/{id}")
public User updateUserByID(@PathVariable("id")Long id, @RequestBody User user) {
	
		try
		{
			return userService.updateUserById(id, user);
		}
		
		catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	
}



//Delete user by Id
@DeleteMapping("/users/{id}")
public void deleteUserById(@PathVariable("id")Long id) {
	userService.deleteUSerById(id);
}


@GetMapping("/users/byusername/{username}")
public User getUserByName(@PathVariable("username") String username) {
	return userService.getUserByName(username);
	
}

}
