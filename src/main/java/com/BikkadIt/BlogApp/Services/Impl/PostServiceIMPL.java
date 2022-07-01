package com.BikkadIt.BlogApp.Services.Impl;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BikkadIt.BlogApp.Entities.Category;
import com.BikkadIt.BlogApp.Entities.Post;
import com.BikkadIt.BlogApp.Entities.User;
import com.BikkadIt.BlogApp.Exceptions.ResourceNotFoundException;
import com.BikkadIt.BlogApp.Payloads.PostDTO;
import com.BikkadIt.BlogApp.Payloads.PostResponse;
import com.BikkadIt.BlogApp.Services.PostService;
import com.BikkadIt.BlogApp.repositorys.CategoryRepo;
import com.BikkadIt.BlogApp.repositorys.PostRepo;
import com.BikkadIt.BlogApp.repositorys.UserRepo;

@Service
public class PostServiceIMPL implements PostService{
	

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Override
	public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		
		Post post= this.modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
	
		Post newpost=this.postRepo.save(post);
		
		return this.modelMapper.map(newpost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		
		Post save = this.postRepo.save(post);
		 
		
		return this.modelMapper.map(save, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		
		this.postRepo.delete(post);
		
		
	}

	@Override
	
	public List<PostDTO> getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort=null;
		
		if(sortDir.equalsIgnoreCase("asc")){
			
			sort= Sort.by(sortBy).ascending();
			
		}else {
			sort= Sort.by(sortBy).descending();
		}	
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);
		
		
		
	Page<Post> pagePost = this.postRepo.findAll(pageable);
	List<Post> allposts = pagePost.getContent();
		
		List<PostDTO> postDTOs= allposts.stream()
				.map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDTOs);
		postResponse.setPageNumber(pagePost.getNumber());
		
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		
		postResponse.setTotalPages(pagePost.getTotalPages());
		
		postResponse.setLastPage(pagePost.isLast());
		
		return postDTOs;
	}

	@Override
	public PostDTO getpostById(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category ", "categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		
		List<PostDTO> postDTOs = posts.stream().map((post -> this.modelMapper.map(post, PostDTO.class)))
				.collect(Collectors.toList());
		
		return postDTOs;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDTO> postDTOs = posts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		
		return postDTOs;
	}

	//searching
	@Override
	public List<PostDTO> searchPosts(String keyWords) {
		// TODO Auto-generated method stub
		
		List<Post> posts = this.postRepo.findByTitleContaining(keyWords);
		
		List<PostDTO> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}
	
	

}
