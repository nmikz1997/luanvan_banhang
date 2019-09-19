package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Attribute;

public interface AttributeService {
	//save
	Attribute save(Attribute attribute);
	
	//findAll
	List<Attribute> findAll();
	
	List<Attribute> findByCategory(Long categoryId);
}
