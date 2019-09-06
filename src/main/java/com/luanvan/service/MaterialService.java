package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Material;

public interface MaterialService {
	List<Material> findAll();
	
	List<Material> findByName(String name);
	
	Material findById(Long id);
	
	Material create(Material material);

	Material update(Material material, Long id);
	
	void delete(Long id);
}
