package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public void save(@RequestBody AttributeValue attributeValue) {
		attributeValueService.save(attributeValue);
	};
	
	@GetMapping
	public List<AttributeValue> findAll() {
		return attributeValueService.findAll();
	};
}
