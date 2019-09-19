package com.luanvan.dto.response;

import java.util.Date;
import java.util.List;

import com.luanvan.model.Category;
import com.luanvan.model.Material;
import com.luanvan.model.Origin;
import com.luanvan.model.Picture;
import com.luanvan.model.Producer;
import com.luanvan.model.Promotion;
import com.luanvan.model.Store;

import lombok.Getter;
import lombok.Setter;

public class ProductDetailDTO {
	@Getter @Setter private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String avatar;
	@Getter @Setter private String derciption;
	@Getter @Setter private int avgStar;
	@Getter @Setter private Integer price;
	@Setter 		private Integer priceNew;
	@Getter @Setter private Integer quantity;
	@Setter @Getter private Store store;
	@Setter @Getter private Producer producer;
	@Setter @Getter private Material material;
	@Setter @Getter private Origin origin;
	@Setter @Getter private Category category;
	@Setter 		private List<Promotion> promotions;
	@Setter @Getter private List<Picture> pictures;
	
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
		return promotionActive;//trả về thông tin khuyến mãi đang áp dụng
	}

	public Integer getPriceNew() { //giá áp dụng
		return (priceNew*(100 - getMaxPromotion().getSaleOff()))/100;
	}
}
