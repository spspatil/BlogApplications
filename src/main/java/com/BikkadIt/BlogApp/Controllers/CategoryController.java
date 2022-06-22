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
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIt.BlogApp.Payloads.ApiResponse;
import com.BikkadIt.BlogApp.Payloads.CategoryDTO;
import com.BikkadIt.BlogApp.Services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	//create
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){

		CategoryDTO createcategory = this.categoryService.createcategory(categoryDTO);
		
		return new ResponseEntity<CategoryDTO>(createcategory,HttpStatus.CREATED);
		
	}
	
	//upadte
	
	@PutMapping("/{catgoryId}")
	public ResponseEntity<CategoryDTO> upadteCategory(@RequestBody CategoryDTO categoryDTO , @PathVariable Integer catgoryId){
		
		CategoryDTO updateCategory= this.categoryService.updateCategoryDTO(categoryDTO, catgoryId);
		
		return new ResponseEntity<CategoryDTO>(updateCategory,HttpStatus.OK);
		
	}
	
	//delete
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("category Deleted successfully", true),HttpStatus.OK);
	}
	
	//get
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId){
		
		CategoryDTO categoryDTO = this.categoryService.getSingleCategoryDTO(categoryId);
		
		return new ResponseEntity<CategoryDTO>(categoryDTO , HttpStatus.OK);
		
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllcategory(){
		
		List<CategoryDTO> allcategory = this.categoryService.getAllcategory();
		return new ResponseEntity<List<CategoryDTO>>(allcategory , HttpStatus.OK);
	}

}
