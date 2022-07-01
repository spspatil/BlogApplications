package com.BikkadIt.BlogApp.Services;

import com.BikkadIt.BlogApp.Payloads.CommentDto;

public interface CommentService {
	
	
	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);
}
