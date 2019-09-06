package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Origin;

public interface OriginService {
	
	List<Origin> findAll();
	
	List<Origin> findByName(String name);
	
	Origin findById(Long id);
	
	Origin create(Origin origin);

	Origin update(Origin origin, Long id);
	
	void delete(Long id);
}
