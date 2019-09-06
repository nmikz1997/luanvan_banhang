package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Promotion;

public interface PromotionService {
	
	List<Promotion> findAll();
	
	List<Promotion> findByName(String name);
	
	Promotion findById(Long id);
	
	Promotion create(Promotion promotion);

	Promotion update(Promotion promotion, Long id);
	
	void delete(Long id);
	
}
