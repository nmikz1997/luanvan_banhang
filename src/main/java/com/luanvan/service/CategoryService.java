package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Category;
public interface CategoryService {
	
	List<Category> findAll();
	
	List<Category> findByName(String name);
	
	Category findById(Long id);
	
	//List<Category> getByParent(Long id);
	
	Category create(Category category);

	Category update(Category category, Long id);
	
	void delete(Long id);

	List<Category> findByParent(Long parentid);
}
