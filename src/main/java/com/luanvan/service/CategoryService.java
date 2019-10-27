package com.luanvan.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.luanvan.model.Category;
public interface CategoryService {
	
	List<Category> findAll();
	
	List<Category> findByName(String name);
	
	Category findById(Long id);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Category create(Category category);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Category update(Category category, Long id);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void delete(Long id);

	List<Category> findByParent(Long parentid);
}
