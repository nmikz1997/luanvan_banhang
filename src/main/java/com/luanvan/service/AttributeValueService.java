package com.luanvan.service;

import java.util.List;

import com.luanvan.model.AttributeValue;

public interface AttributeValueService {
	//them attribute value
	AttributeValue save(AttributeValue attributeValue);
	
	//tim Attribute theo product
	List<AttributeValue> findByProduct(Long id);
	
	//
	List<AttributeValue> findByAttribute(Long attributeId);
}
