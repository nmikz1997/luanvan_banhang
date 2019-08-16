package com.luanvan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.Category;
import com.luanvan.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	private CategoryService categoryService;
	
	@Autowired
	public CategoryController (CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("{id}")
	public Category show(@PathVariable(name = "id") Long id) {
		return categoryService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Category create(@Valid @RequestBody Category category){
		return categoryService.create(category);
	}

	@PutMapping("/{id}")
	public Category update(@PathVariable Long id, @Valid @RequestBody Category category) {
		return categoryService.update(category, id);
	}

}