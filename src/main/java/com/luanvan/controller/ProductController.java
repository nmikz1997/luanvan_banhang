package com.luanvan.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.request.SaveDetailProductDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.model.Product;
import com.luanvan.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController (ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<Product> findAll() {
		return productService.findAll();
	}
	
	@GetMapping("test")
	public Set<ProductDTO> selectAll() {
		return productService.selectAll();
	}
	
	@GetMapping("{id}")
	public Map<String,Object> findById(@PathVariable("id") Long id) {
		return productService.findById(id);
	}
	
	@GetMapping("test/{id}")
	public ProductDTO findDTOById(@PathVariable("id") Long id) {
		return productService.findDTOById(id);
	}
	
	@GetMapping("/category/{id}")
	public List<Product> findByCategory(@PathVariable("id") Long categoryId) {
		return productService.findByCategory(categoryId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void save(@RequestBody SaveDetailProductDTO productDetail) {
		productService.save(productDetail);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		productService.delete(id);
	}


}
