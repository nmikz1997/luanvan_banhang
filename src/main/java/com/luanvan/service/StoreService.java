package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Store;
public interface StoreService {
	
	List<Store> findAll();
	
	List<Store> findByName(String name);
	
	Store findById(Long id);
	
	Store create(Store origin);

	Store update(Store origin, Long id);
	
	void delete(Long id);
}
