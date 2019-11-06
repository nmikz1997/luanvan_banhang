package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.request.CreateStoreDTO;
import com.luanvan.dto.response.StoreDTO;
import com.luanvan.model.Store;
public interface StoreService {
	
	List<StoreDTO> findAll();
	
	List<Store> findByName(String name);
	
	Store findById(Long id);
	
	void create(CreateStoreDTO store);

	Store update(Store origin, Long id);
	
	void delete(Long id);

	void updateStatus(Store status, Long id);
}
