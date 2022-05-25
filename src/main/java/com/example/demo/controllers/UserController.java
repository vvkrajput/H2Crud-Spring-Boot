package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
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
     public User createUser(@RequestBody User user)
     {
    	 return userService.createUser(user);
    	 
     }

//getUSerByID

@GetMapping("/users/{id}")
public Optional<User>getUserById(@PathVariable("id") Long id){
	return userService.getUserById(id);
	
}
	

//UpdateuserById


@PutMapping("user/{id}")
public User updateUserByID(@PathVariable("id")Long id, @RequestBody User user) {
	return userService.updateUserById(id, user);
	
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
