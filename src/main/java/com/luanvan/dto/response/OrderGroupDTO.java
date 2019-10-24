package com.luanvan.dto.response;

import java.util.Date;
import java.util.List;

import com.luanvan.model.Customer;
import com.luanvan.model.OrderStatus;
import com.luanvan.model.PaymentType;

import lombok.Getter;
import lombok.Setter;

public class OrderGroupDTO {
	@Setter @Getter private Long id;
	@Setter @Getter private String address;
	@Setter @Getter private Date createdAt;
	@Setter @Getter private PaymentType paymentType;
	@Setter @Getter private Customer customer;
	@Setter @Getter private List<Order> orders;
	
	@Setter @Getter
	public static class Order{
		private Integer total;
		private OrderStatus orderStatus;
		private List<OrderDetail> ordersDetail;
		
		@Setter @Getter
		public static class OrderDetail{
			
			private Product product;
			
			@Setter @Getter
			public static class Product{
				private String avatar;
			}
		}
	}

	public Integer getTotalGroup() {
		Integer totalGroup = 0;
		for(Order order : orders) {
			totalGroup += order.getTotal();
		}
		return totalGroup;
	}
	
	
}
