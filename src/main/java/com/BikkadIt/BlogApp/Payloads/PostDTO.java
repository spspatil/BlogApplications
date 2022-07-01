package com.BikkadIt.BlogApp.Payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.BikkadIt.BlogApp.Entities.Category;
import com.BikkadIt.BlogApp.Entities.Comment;
import com.BikkadIt.BlogApp.Entities.User;

public class PostDTO {
	
	private Integer postId;
	
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	private String title;
	
	private String content;


	private String imageName;
	
	private Date addedDate;
	
	private CategoryDTO category;
	
	private UserDTO user;
	
	private Set<Comment> comments=new HashSet<>(); 

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public PostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


}
