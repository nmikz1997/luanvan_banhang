package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Category;
import com.luanvan.repo.CategoryRepository;
import com.luanvan.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Category> getByParent(Long parentid) {
		return categoryRepository.findByParentId(parentid);
	}

	@Override
	public List<Category> findByName(String name) {
		return categoryRepository.findByNameContaining(name);
	}

	@Override
	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category update(Category category, Long id) {
//		if(category.getId() != id) {
//			throw new IdMismatchException();
//		}
		categoryRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Long id) {
		categoryRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		categoryRepository.deleteById(id);
	}
	
}
