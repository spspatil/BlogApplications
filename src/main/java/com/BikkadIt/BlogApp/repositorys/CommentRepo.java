package com.BikkadIt.BlogApp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIt.BlogApp.Entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
	

}
