package com.BikkadIt.BlogApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIt.BlogApp.Payloads.UserDTO;
import com.BikkadIt.BlogApp.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto){
		UserDTO createUser = this.userService.createUser(userDto);
		
		return new ResponseEntity<UserDTO>(createUser,HttpStatus.CREATED);
		
	}
	
	
	

}
