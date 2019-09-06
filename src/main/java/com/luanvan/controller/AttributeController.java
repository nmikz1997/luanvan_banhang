package com.luanvan.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.Attribute;
import com.luanvan.service.AttributeService;

@RestController
@RequestMapping("attributes")
public class AttributeController {
	private AttributeService attributeService;
	
	@Autowired
	public AttributeController (AttributeService attributeService) {
		this.attributeService = attributeService;
	}
	
	@GetMapping
	public HashSet<Object> findAll() {
		return attributeService.findAll();
	}
	
	@PostMapping
	public Attribute save(@RequestBody Attribute attribute) {
		return attributeService.save(attribute);
	}
	
}
