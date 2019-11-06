package com.luanvan.dto.response;


import java.util.Date;
import java.util.List;

import com.luanvan.model.OrderDetail;
import com.luanvan.model.Price;
import com.luanvan.model.Promotion;
import com.luanvan.model.Store;

import lombok.Getter;
import lombok.Setter;

public class ProductDTO {
	
	@Getter @Setter private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private float avgStar;
	@Getter @Setter private int status;
	@Getter @Setter private String avatar;//ảnh đại diện
	@Getter @Setter private Integer price;//giá gốc
	@Setter private Integer priceNew;//giá mới
	@Getter @Setter private Integer quantity;//số lượng gốc
	@Getter @Setter private int soLuongMua;//số lượng mua
	@Setter @Getter private Store store;
	@Setter @Getter private Category category;
	@Setter @Getter private Producer producer;
	@Setter @Getter private Origin origin;
	@Setter private List<Promotion> promotions;//danh sách khuyến mãi
	@Setter private List<Price> prices;//danh sách giá
	@Setter private List<OrderDetail> ordersDetails;
	
	@Getter @Setter
	public static class Category {
		private Long id;
		private String name;
		private String plug;
	}
	@Getter @Setter
	public static class Producer {
		private Long id;
		private String name;
		private String plug;
	}
	@Getter @Setter
	public static class Origin {
		private Long id;
		private String name;
		private String plug;
	}
	
	public int getSold() {
		int sold = 0;
		for(OrderDetail detail : ordersDetails) {
			if(detail.getOrder().getOrderStatus().getId() < 6)
				sold += detail.getQuantity();
		}
		return sold;
	}
	
	public boolean canBuy() {
		if(getQuantity() - (getSold() + getSoLuongMua()) >= 0)
			return true;
		return false;
	}
	
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
	
	//get giá mới nhất

	public Integer getPriceNew() {
		return (priceNew*(100 - getMaxPromotion().getSaleOff() ))/100;
	}
	
	public Price getPriceApply() {
		Long max = (long) 0;
		Price price = null;
        
        for (Price pr : this.prices) {
            if(pr.getId() > max) {
            	max = pr.getId();
            	price = pr;
            }
        }
        return price;
	}
}
