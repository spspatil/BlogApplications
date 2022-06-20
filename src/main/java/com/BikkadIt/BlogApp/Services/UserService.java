package com.BikkadIt.BlogApp.Services;

import java.util.List;

import com.BikkadIt.BlogApp.Payloads.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user,Integer userId);
	UserDTO getuserByid(Integer userId);
	List<UserDTO> getAllUser();
	
	void deleteUser(Integer userId);

}
