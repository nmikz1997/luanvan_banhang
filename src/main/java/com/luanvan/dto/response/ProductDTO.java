package com.luanvan.dto.response;


import java.util.Date;
import java.util.List;

import com.luanvan.model.Promotion;

import lombok.Getter;
import lombok.Setter;

public class ProductDTO {
	
	@Getter @Setter private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String avatar;//ảnh đại diện
	@Getter @Setter private Integer price;//giá gốc
	@Setter private Integer priceNew;//giá mới
	@Getter @Setter private Integer quantity;//giá gốc
	@Setter private List<Promotion> promotions;//danh sách khuyến mãi
	
	public Promotion getMaxPromotion() {
		Integer safeOffMax = 0;
		Date today = new Date();
		Promotion promotionActive = new Promotion();
		
		for(Promotion promotion : promotions) {
			
			if(promotion.getDayStart().before(today) 
				&& promotion.getDayEnd().after(today)
				&& promotion.getSaleOff() > safeOffMax) {
				
					safeOffMax = promotion.getSaleOff();
					promotionActive = promotion;
			
			}
		}
		return promotionActive;//trả về khuyến mãi đang áp dụng
	}

	public Integer getPriceNew() {
		return (priceNew*(100 - getMaxPromotion().getSaleOff() ))/100;
	}
}
