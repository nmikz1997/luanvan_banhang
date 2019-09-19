package com.luanvan.dto.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDTO {
	private Long id;
	private String name;
	private Set<AttributeValue> attributeValues;
	private Set<Category> categories;
	
	@Setter
	@Getter
	public static class AttributeValue{
		private Long id;
		private String name;
	}
	
	@Setter
	@Getter
	public static class Category{
		private Long id;
		private String name;
	}
}
