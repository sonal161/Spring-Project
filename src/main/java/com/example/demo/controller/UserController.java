package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2/")
public class UserController {

@Autowired
private UserRepository userRepository;

@GetMapping("/users")
public List<User> getAllUsers(){
	return userRepository.findAll();
}
@PostMapping("/users")
public User createUser(@RequestBody User user) {
	return userRepository.save(user);
}

@GetMapping("/users/{user}/{password}")
public ResponseEntity<User> getUserById(@PathVariable String user, @PathVariable String password) 
{
	try {
	User user1 = userRepository.findById(user)
			.orElseThrow(() -> new ResourceNotFoundException("User does not exist: " + user));
	
	if(user1 != null) {
		
		boolean pswd = user1.getPassword().matches(password);
		//User pswd = userRepository.findById(password).orElseThrow(() -> new ResourceNotFoundException("User does not exist: " + user));;
				
		if(pswd) {
			return ResponseEntity.ok(user1);
		}
		return ResponseEntity.ok(null);
		}
	
	return ResponseEntity.ok(user1);
	}
	catch(Exception e) {
		return ResponseEntity.ok(null);
	}

}
@GetMapping("/users/{user}")
public ResponseEntity<User> getEmployById(@PathVariable String user) {
	User user1 = userRepository.findById(user)
			.orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + user));
	return ResponseEntity.ok(user1);
}
//@GetMapping("/users")
//public ResponseEntity<User> getUserById1(@PathVariable String user1){
//	User user=userRepository.findById(user1)
//		.orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + user1));
//	return ResponseEntity.ok(user);
//}


//@PutMapping("/users")
//public ResponseEntity<User> getUserById(@PathVariable String user1){
//	User user=userRepository.findById(user1)
//			.orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + user1));
//	return ResponseEntity.ok(user);
// 
//
//}
@DeleteMapping("/users/{user1}")
public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String user1) {
	User user = userRepository.findById(user1)
			.orElseThrow(() -> new ResourceNotFoundException("Usre not exist with id : " + user1));
userRepository.delete(user);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);
}
}
