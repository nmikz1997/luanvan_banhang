package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.AttributeValue;
import com.luanvan.repo.AttributeValueRepository;
import com.luanvan.service.AttributeValueService;


@Service
public class AttributeValueServiceImpl implements AttributeValueService {

	private AttributeValueRepository attributeValueRepository;
	
	@Autowired
	public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository) {
		this.attributeValueRepository = attributeValueRepository;
	}

	@Override
	public void save(AttributeValue attributeValue) {
		attributeValueRepository.save(attributeValue);
	}

	@Override
	public List<AttributeValue> findByProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttributeValue> findByAttribute(Long attributeId) {
		return attributeValueRepository.findByAttributeId(attributeId);
	}

	@Override
	public List<AttributeValue> findAll() {
		// TODO Auto-generated method stub
		return attributeValueRepository.findAll();
	}

}
