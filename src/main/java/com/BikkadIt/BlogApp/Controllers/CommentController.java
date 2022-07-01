package com.BikkadIt.BlogApp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIt.BlogApp.Payloads.ApiResponse;
import com.BikkadIt.BlogApp.Payloads.CommentDto;
import com.BikkadIt.BlogApp.Services.CommentService;

@RestController
@RequestMapping("/api/com")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comment/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto ,@PathVariable Integer postId){
		
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		
		
		return new ResponseEntity<CommentDto>(createComment ,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment delted Sucessfully", true),HttpStatus.OK);
	}
}
