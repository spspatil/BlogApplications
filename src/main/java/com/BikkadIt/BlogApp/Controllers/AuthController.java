package com.BikkadIt.BlogApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIt.BlogApp.Payloads.JwtAuthRequest;
import com.BikkadIt.BlogApp.Payloads.JwtAuthResponse;
import com.BikkadIt.BlogApp.Security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login/s")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		
		System.out.println("+++++++++++++++request"+request);
		
		this.authenticate(request.getUserName(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response =new JwtAuthResponse();
		response.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(response , HttpStatus.OK);
		
	}

	private void authenticate(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authenticationToken
		=new UsernamePasswordAuthenticationToken(userName, password);
		
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}
		 catch (BadCredentialsException e) {
			// TODO: handle exception
			 System.out.println("invalid details !!");
			 throw new Exception("Invalid Username or Password");
		}
		}
	
}
