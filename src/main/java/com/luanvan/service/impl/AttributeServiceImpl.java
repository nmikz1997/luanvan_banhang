package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.Attribute;
import com.luanvan.repo.AttributeRepository;
import com.luanvan.service.AttributeService;

@Service
public class AttributeServiceImpl implements AttributeService {

	private AttributeRepository attributeRepository;
	
	@Autowired
	public AttributeServiceImpl(AttributeRepository attributeRepository) {
		this.attributeRepository = attributeRepository;
	}
	
	@Override
	public Attribute save(Attribute attribute) {
		System.out.println(attribute);
		return attributeRepository.save(attribute);
	}

	@Override
	public List<Attribute> findAll() {
		return attributeRepository.findAll();
//		HashSet<Object> data = new HashSet<>();
//		return data;
	}

	@Override
	public List<Attribute> findByCategory(Long categoryId) {
		return attributeRepository.findByCategoriesId(categoryId);
	}

}
