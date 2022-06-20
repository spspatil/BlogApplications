package com.BikkadIt.BlogApp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIt.BlogApp.Entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	
	

}
