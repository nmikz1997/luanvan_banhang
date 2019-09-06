package com.luanvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.AttributeValue;
import com.luanvan.service.AttributeValueService;

@RestController
@RequestMapping("attribute-values")
public class AttributeValueController {
	
	private AttributeValueService attributeValueService;
	
	@Autowired
	public AttributeValueController(AttributeValueService attributeValueService) {
		this.attributeValueService = attributeValueService;
	}
	
	@PostMapping
	public AttributeValue save(@RequestBody AttributeValue attributeValue) {
		return attributeValueService.save(attributeValue);
	};
}
