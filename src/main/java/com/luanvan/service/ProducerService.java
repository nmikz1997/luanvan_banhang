package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Producer;

public interface ProducerService {
	
	List<Producer> findAll();
	
	List<Producer> findByName(String name);
	
	Producer findById(Long id);
	
	Producer create(Producer producer);

	Producer update(Producer producer, Long id);
	
	void delete(Long id);
	
}
