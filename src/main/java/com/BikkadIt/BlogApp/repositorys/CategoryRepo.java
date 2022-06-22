package com.BikkadIt.BlogApp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BikkadIt.BlogApp.Entities.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer>{

	
}
