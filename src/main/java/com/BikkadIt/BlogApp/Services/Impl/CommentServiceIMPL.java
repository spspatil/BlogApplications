package com.BikkadIt.BlogApp.Services.Impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIt.BlogApp.Entities.Comment;
import com.BikkadIt.BlogApp.Entities.Post;
import com.BikkadIt.BlogApp.Exceptions.ResourceNotFoundException;
import com.BikkadIt.BlogApp.Payloads.CommentDto;
import com.BikkadIt.BlogApp.Services.CommentService;
import com.BikkadIt.BlogApp.repositorys.CommentRepo;
import com.BikkadIt.BlogApp.repositorys.PostRepo;

@Service
public class CommentServiceIMPL implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		Comment save = this.commentRepo.save(comment);
		
		return this.modelMapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment comment = this.commentRepo.findById(commentId)
		.orElseThrow(()-> new ResourceNotFoundException("comment", "commentId", commentId));
		
		this.commentRepo.delete(comment);
		
	}

}
