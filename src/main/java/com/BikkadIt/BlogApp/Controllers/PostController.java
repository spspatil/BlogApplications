package com.BikkadIt.BlogApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIt.BlogApp.Entities.Post;
import com.BikkadIt.BlogApp.Payloads.ApiResponse;
import com.BikkadIt.BlogApp.Payloads.PostDTO;
import com.BikkadIt.BlogApp.Services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService postService;
	
	//create 
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,@PathVariable Integer userId, @PathVariable Integer categoryId){
		
		
		PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);
		
		return new ResponseEntity<PostDTO>(createPost,HttpStatus.CREATED);
	}
	

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
		
		List<PostDTO> postByUser = this.postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDTO>>(postByUser,HttpStatus.OK);
		
	}
	

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostBycategory(@PathVariable Integer categoryId){
		
		List<PostDTO> postBycategory = this.postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDTO>>(postBycategory,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getallPost(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
			@RequestParam( value = "pageSize" ,defaultValue = "5",required = false)Integer pageSize){
		
		List<PostDTO> allPost = this.postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<List<PostDTO>>(allPost,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId){
		
		PostDTO updatePost = this.postService.updatePost(postDTO, postId);
		
		return new ResponseEntity<PostDTO>(updatePost , HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId){
		
		this.postService.deletePost(postId);
		
		return new ApiResponse("post Deleted Ssuccessfully " ,true );
		
	}
}
