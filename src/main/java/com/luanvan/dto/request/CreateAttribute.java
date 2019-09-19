package com.luanvan.dto.request;

import java.util.Set;

import com.luanvan.model.Attribute;
import com.luanvan.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttribute {
	
	private Attribute attribute;
	private Set<Category> categories;
	
}
