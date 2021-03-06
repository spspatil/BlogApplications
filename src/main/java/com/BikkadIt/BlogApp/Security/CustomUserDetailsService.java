package com.BikkadIt.BlogApp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BikkadIt.BlogApp.Entities.User;
import com.BikkadIt.BlogApp.Exceptions.ResourceNotFoundException;
import com.BikkadIt.BlogApp.repositorys.UserRepo;

@Service
public class CustomUserDetailsService  implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//loading user from database by username
		
		User user = this.userRepo.findByEmail(username)
		.orElseThrow(()-> new ResourceNotFoundException("user", "email"+username, 0));
		return user;
	}

}
