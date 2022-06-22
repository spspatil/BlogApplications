package com.BikkadIt.BlogApp.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name = "title" , length = 100, nullable = false)
	private String categoryTitle;
	
	@Column(name = "description")
	private String categoryDescreption;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer  getCategoryId() {
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
