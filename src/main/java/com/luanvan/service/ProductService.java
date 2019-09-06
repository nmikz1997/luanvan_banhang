package com.luanvan.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.luanvan.dto.request.SaveDetailProductDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.model.Product;

public interface ProductService {
	
	List<Product> findAll();
	
	Set<ProductDTO> selectAll();
	
	//List<Product> findByStore();
	
	List<Product> findByName(String name);
	
	Map<String,Object> findById(Long id);
	
	ProductDTO findDTOById(Long id);
	
	void save(SaveDetailProductDTO productDetail);
	
	List<Product> findByCategory(Long categoryId);
	
	void delete(Long id);
	
	void deleteByCategoryId(Long categoryId);
}
