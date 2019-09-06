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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7797599881073041071L;

	@Column(name = "promotion_id")
	private Long promotionId;
	
	@Column(name = "product_id")
	private Long productId;
}
