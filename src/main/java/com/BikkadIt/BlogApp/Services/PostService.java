package com.BikkadIt.BlogApp.Services;

import java.util.List;

import com.BikkadIt.BlogApp.Entities.Post;
import com.BikkadIt.BlogApp.Payloads.PostDTO;
import com.BikkadIt.BlogApp.Payloads.PostResponse;

public interface PostService {
	
	
	//create 
	PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
	
	//update 
	
	PostDTO updatePost(PostDTO postDTO,Integer postId);
	
	//delete
	
	void deletePost(Integer postId);
	
	//getAllPost
	
	List<PostDTO> getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//getpost by single id
	PostDTO getpostById(Integer postId);
	
	//get all post by category
	
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	//get All posts by User
	
	List<PostDTO> getPostByUser(Integer userId);
	
	//Search
	 
	List<PostDTO> searchPosts(String keyWords);
	
	
	
	
	

}
