package com.luanvan.dto.request;

import java.util.Set;

import com.luanvan.model.AttributeValue;
import com.luanvan.model.Price;
import com.luanvan.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProduct {
	
	private Product product;
	
	private Set<AttributeValue> attributeValues;

	public CreateProduct(Product product, Set<AttributeValue> attributeValues) {
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
