package com.luanvan.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.response.AttributeDTO;
import com.luanvan.model.Attribute;
import com.luanvan.model.Promotion;
import com.luanvan.service.AttributeService;

@RestController
@RequestMapping("attributes")
public class AttributeController {
	private AttributeService attributeService;
	
	@Autowired
	public AttributeController (AttributeService attributeService) {
		this.attributeService = attributeService;
	}
	
	@GetMapping("category/{categoryId}")
	public List<Attribute> findByCategory(@PathVariable Long categoryId) {
		return attributeService.findByCategory(categoryId);
	}
	
	@GetMapping()
	public List<Attribute> findAll() {
		return attributeService.findAll();
	}
	
	@PostMapping
	public Attribute save(@RequestBody Attribute req) {
		//ModelMapper modelMapper = new ModelMapper();
 		//Attribute attribute = modelMapper.map(req, Attribute.class);
		return attributeService.save(req);
	}
	
}
