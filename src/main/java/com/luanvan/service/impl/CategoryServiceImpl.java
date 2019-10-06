package com.luanvan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Category;
import com.luanvan.repo.CategoryRepository;
import com.luanvan.service.CategoryService;
import com.luanvan.service.ProductService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryRepository categoryRepository;
	
	private ProductService productService;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService) 
	{
		this.categoryRepository = categoryRepository;
		this.productService = productService;
	}
	
	@Override
	public List<Category> findAll() {
		//System.out.println(categoryRepository.findAll());
		return categoryRepository.findByStatus(true);
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Category> findByParent(Long parentid) {
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
//			throw new IdMismatchException(); //id không hợp lệ, id gửi lên không giống với id trên form (dto)
//		}
		categoryRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		
		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		Category categoryUpdate = findById(id);
		
		categoryUpdate.setStatus(false);
		
		update(categoryUpdate, id);
		
		productService.deleteByCategoryId(id);

	}
	
}
