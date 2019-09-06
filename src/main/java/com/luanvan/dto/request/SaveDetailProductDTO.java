package com.luanvan.dto.request;

import java.util.Set;

import com.luanvan.model.AttributeValue;
import com.luanvan.model.Product;

public class SaveDetailProductDTO {
	
	private Product product;
	
	private Set<AttributeValue> attributeValues;
	
	public SaveDetailProductDTO() {
		
	}

	public SaveDetailProductDTO(Product product, Set<AttributeValue> attributeValues) {
		this.product = product;
		this.attributeValues = attributeValues;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(Set<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}
	
}
