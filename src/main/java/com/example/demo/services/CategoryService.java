package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.models.Category;

public interface CategoryService {
	List<Category> getAll();
	Boolean create(Category category);
	Category findById(Integer id);
	Boolean update(Category category);
	Boolean delete(Integer id);
	List<Category> searchCategory(String keyword);
	
	Page<Category> getAll(Integer pageNo);
	Page<Category> searchCategory(String keyword,Integer pageNo);
	List<Category> getAllByStatus();
}
