package com.luanvan.service;

import java.util.HashSet;

import com.luanvan.model.Attribute;

public interface AttributeService {
	//save
	Attribute save(Attribute attribute);
	
	//findAll
	HashSet<Object> findAll();
}
