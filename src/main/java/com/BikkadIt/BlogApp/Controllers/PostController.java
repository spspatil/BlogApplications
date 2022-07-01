package com.BikkadIt.BlogApp.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.BikkadIt.BlogApp.Config.AppConstants;
import com.BikkadIt.BlogApp.Entities.Post;
import com.BikkadIt.BlogApp.Payloads.ApiResponse;
import com.BikkadIt.BlogApp.Payloads.PostDTO;
import com.BikkadIt.BlogApp.Payloads.PostResponse;
import com.BikkadIt.BlogApp.Services.FileService;
import com.BikkadIt.BlogApp.Services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
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
	
	@GetMapping("/posts/all")
	public ResponseEntity<List<PostDTO>> getallPost(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam( value = "pageSize" ,defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam( value = "sortBy" ,defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam (value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir){
		
		 List<PostDTO> allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
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
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> postByTitle(@PathVariable String keywords){
		
		List<PostDTO> searchPosts = this.postService.searchPosts(keywords);
		
		return new ResponseEntity<List<PostDTO>>(searchPosts ,HttpStatus.OK);
	}
	
	//post image upload
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDTO postDTO = this.postService.getpostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		
		postDTO.setImageName(fileName);
		
		PostDTO updatePost = this.postService.updatePost(postDTO, postId);
		
		return new ResponseEntity<PostDTO>(updatePost ,HttpStatus.OK);
		
		
	}
	
	//method to serve files
	@GetMapping(value = "/post/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
	public void dowloadimage(@PathVariable("imageName") String imageName ,HttpServletResponse response) throws IOException {
		
		InputStream resource=this.fileService.getresource(path ,imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
}
