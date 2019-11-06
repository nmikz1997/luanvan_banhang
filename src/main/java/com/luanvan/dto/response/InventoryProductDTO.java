package com.luanvan.dto.response;

import java.util.Date;
import java.util.List;

import com.luanvan.model.OrderDetail;

import lombok.Getter;
import lombok.Setter;

public class InventoryProductDTO {
	
	@Setter @Getter private Long id;
	@Setter @Getter private String name;
	@Getter @Setter private Integer quantity;
	@Setter @Getter private List<Inventory> inventories;
	@Setter private List<OrderDetail> ordersDetails;
	
	@Setter @Getter
	public static class Inventory{
		private Long id;
		private int quantity;
		private Date importDate;
		private Date createdAt;
	}
	
	public int getSold() {
		int sold = 0;
		for(OrderDetail detail : ordersDetails) {
			sold += detail.getQuantity();
		}
		return sold;
	}
	
}
