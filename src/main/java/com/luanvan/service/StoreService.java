package com.luanvan.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.luanvan.dto.request.CreateStoreDTO;
import com.luanvan.dto.response.StoreDTO;
import com.luanvan.model.Store;
public interface StoreService {
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<StoreDTO> findAll();
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<StoreDTO> findAllActive();
	
	List<Store> findByName(String name);
	
	Store findById(Long id);
	
	void create(CreateStoreDTO store);

	Store update(Store origin, Long id);
	
	void delete(Long id);

	void updateStatus(Store status, Long id);
}
