package com.BikkadIt.BlogApp.Services;

import java.util.List;

import com.BikkadIt.BlogApp.Entities.Post;
import com.BikkadIt.BlogApp.Payloads.PostDTO;

public interface PostService {
	
	
	//create 
	PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
	
	//update 
	
	Post updatePost(PostDTO postDTO,Integer postId);
	
	//delete
	
	void deletePost(Integer postId);
	
	//getAllPost
	
	List<Post> getAllPost();
	
	//getpost by single id
	Post getpostById(Integer postId);
	
	//get all post by category
	
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	//get All posts by User
	
	List<PostDTO> getPostByUser(Integer userId);
	
	
	
	

}
