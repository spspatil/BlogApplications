package com.BikkadIt.BlogApp.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.BikkadIt.BlogApp.Entities.User;
import com.BikkadIt.BlogApp.Payloads.UserDTO;
import com.BikkadIt.BlogApp.Services.UserService;
import com.BikkadIt.BlogApp.repositorys.UserRepo;

public class UserserviceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	
	private User dtoToUser(UserDTO userDTO) {
		User user=new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		
		return user;
		
	}

	private UserDTO userTouserDTO(User user) {
		UserDTO userdto=new UserDTO();
		userdto.setId(user.getId());
		userdto.setName(user.getName());
		userdto.setEmail(user.getEmail());
		userdto.setPassword(user.getPassword());
		userdto.setAbout(user.getAbout());
		
		return userdto;
		
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getuserByid(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}

}
