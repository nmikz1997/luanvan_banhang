package com.luanvan.dto.response;

import java.util.List;

import com.luanvan.model.AttributeValue;

public class AttributeResponseDTO {
	private Long id;
	private String name;
	private List<AttributeValue> attributeValues;
		
	public AttributeResponseDTO() {

	}
	public AttributeResponseDTO(Long id, String name, List<AttributeValue> attributeValues) {
		this.id = id;
		this.name = name;
		this.attributeValues = attributeValues;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}
	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}
	
	
}
