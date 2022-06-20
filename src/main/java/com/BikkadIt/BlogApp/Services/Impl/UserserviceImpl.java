package com.BikkadIt.BlogApp.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIt.BlogApp.Entities.User;
import com.BikkadIt.BlogApp.Exceptions.ResourceNotFoundException;
import com.BikkadIt.BlogApp.Payloads.UserDTO;
import com.BikkadIt.BlogApp.Services.UserService;
import com.BikkadIt.BlogApp.repositorys.UserRepo;

@Service
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
	public UserDTO createUser(UserDTO userdto) {
		// TODO Auto-generated method stub
		
		User user= this.dtoToUser(userdto);
		User saveUser=this.userRepo.save(user);
		return this.userTouserDTO(saveUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		user.setName(userdto.getEmail());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		
		User save = this.userRepo.save(user);
		UserDTO userTouserDTO = this.userTouserDTO(save);
		return userTouserDTO;
	}

	@Override
	public UserDTO getuserByid(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));
		return this.userTouserDTO(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDTO> userDTOs = users.stream().map(user-> this.userTouserDTO(user)).collect(Collectors.toList());
		return userDTOs;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		
	}

}
