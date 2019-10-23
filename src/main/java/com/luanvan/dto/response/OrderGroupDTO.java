package com.luanvan.dto.response;

import java.util.ArrayList;
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
	@Setter private List<Order> orders;
	
	@Setter @Getter
	public static class Order{
		private Integer total;
		private OrderStatus orderStatus;
	}

	public Integer getTotalGroup() {
		Integer totalGroup = 0;
		for(Order order : orders) {
			totalGroup += order.getTotal();
		}
		return totalGroup;
	}
	public List<OrderStatus> getOrderStatus() {
		List<OrderStatus> orderStatus = new ArrayList<OrderStatus>();
		orders.forEach(order -> orderStatus.add(order.getOrderStatus()));
		return orderStatus;
	}
	
	
}
