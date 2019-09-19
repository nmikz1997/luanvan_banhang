package com.luanvan.dto.request;

import java.util.Set;

import com.luanvan.model.Product;
import com.luanvan.model.Promotion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromotionDTO {
	private Promotion promotion;
	
	private Set<Product> products;
}
