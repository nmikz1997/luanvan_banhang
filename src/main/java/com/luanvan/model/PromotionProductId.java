package com.luanvan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionProductId implements Serializable{
	
	@Column(name = "promotion_id")
	private Long promotionId;
	
	@Column(name = "product_id")
	private Long productId;
}
