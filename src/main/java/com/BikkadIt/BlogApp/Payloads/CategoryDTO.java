package com.BikkadIt.BlogApp.Payloads;



public class CategoryDTO {
	
	

	private Integer categoryId;
	
	
	private String categoryTitle;
	
	
	private String categoryDescreption;


	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryTitle() {
		return categoryTitle;
	}


	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}


	public String getCategoryDescreption() {
		return categoryDescreption;
	}


	public void setCategoryDescreption(String categoryDescreption) {
		this.categoryDescreption = categoryDescreption;
	}

	
	

}
