package com.luanvan.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.luanvan.dto.response.PromotionDTO;
import com.luanvan.model.Promotion;

public interface PromotionService {
	
	List<Promotion> findAll();
	
	List<Promotion> findByName(String name);
	
	List<Promotion> findByStore(Authentication auth);
	
	PromotionDTO findById(Long id);
	
	Promotion create(Promotion promotion, Authentication auth);

	Promotion update(Promotion promotion, Long id);
	
	void delete(Long id);
	
}
