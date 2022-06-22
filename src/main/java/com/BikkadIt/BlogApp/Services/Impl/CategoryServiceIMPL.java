package com.BikkadIt.BlogApp.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIt.BlogApp.Entities.Category;
import com.BikkadIt.BlogApp.Exceptions.ResourceNotFoundException;
import com.BikkadIt.BlogApp.Payloads.CategoryDTO;
import com.BikkadIt.BlogApp.Services.CategoryService;
import com.BikkadIt.BlogApp.repositorys.CategoryRepo;
@Service
public class CategoryServiceIMPL implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createcategory(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		Category category = this.modelMapper.map(categoryDTO, Category.class);
		
		Category save = this.categoryRepo.save(category);
		
		return this.modelMapper.map(save, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategoryDTO(CategoryDTO categoryDTO,Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDescreption(categoryDTO.getCategoryDescreption());
		//category.setCategoryId(categoryDTO.getCategoryId());
		
		Category save = this.categoryRepo.save(category);
		
		return this.modelMapper.map(save, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getSingleCategoryDTO(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category category = this.categoryRepo.findById(categoryId)
		.orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		return this.modelMapper.map(category , CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category category = this.categoryRepo.findById(categoryId)
		.orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		this.categoryRepo.delete(category);	
	}

	@Override
	public List<CategoryDTO> getAllcategory() {
		// TODO Auto-generated method stub
		
		List<Category> categoryList = this.categoryRepo.findAll();
		
		List<CategoryDTO> categoryDTOs = categoryList.stream()
				.map((cat)->this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		
		
		return categoryDTOs;
	}

}
