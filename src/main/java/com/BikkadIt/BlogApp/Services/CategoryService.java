package com.BikkadIt.BlogApp.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BikkadIt.BlogApp.Payloads.CategoryDTO;


public interface CategoryService {
	
	 //create
     CategoryDTO createcategory(CategoryDTO categoryDTO);
     
     //update 
     CategoryDTO updateCategoryDTO(CategoryDTO categoryDTO ,Integer categoryId);
     
	 //get
     CategoryDTO getSingleCategoryDTO(Integer categoryId);
     
     //delete
     public void deleteCategory(Integer categoryId);
     
	 //getall
     public List<CategoryDTO> getAllcategory();
     
     
     

}
