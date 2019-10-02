package com.luanvan.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	
	@EmbeddedId
	@JsonIgnore
    private OrderDetailId id;
	
    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Order order;
	
	//so luong
    @Column(nullable = false) private int quantity;
    
	//don gia
	@ManyToOne
	@JoinColumn(nullable = false)
    private Price price;
	
	//khuyen mai neu co
	@ManyToOne
	@JoinColumn
    private Promotion promotion;
	
	public int getAmount() {
		return quantity*getPriceNew();
	}
	
	public Integer getPriceNew() {
		if(promotion != null) return (price.getUnitPrice()*(100 - promotion.getSaleOff() ))/100;
		return price.getUnitPrice();
		
	}
}
