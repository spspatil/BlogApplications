package com.BikkadIt.BlogApp.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BikkadIt.BlogApp.Entities.Category;
import com.BikkadIt.BlogApp.Entities.Post;
import com.BikkadIt.BlogApp.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);

}
