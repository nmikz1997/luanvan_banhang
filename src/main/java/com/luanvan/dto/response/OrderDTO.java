package com.luanvan.dto.response;

import java.util.Date;
import java.util.List;

import com.luanvan.model.Customer;
import com.luanvan.model.OrderStatus;
import com.luanvan.model.PaymentType;

import lombok.Getter;
import lombok.Setter;

public class OrderDTO {
	@Setter @Getter private Long id;
	@Setter @Getter private String address;
	@Setter @Getter private OrderStatus orderStatus;
	@Setter @Getter private Customer customer;
	@Setter @Getter private Date createdAt;
	@Setter @Getter private Store store;
	@Setter @Getter private PaymentType paymentType;
	@Setter @Getter private List<OrderDetailDTO> ordersDetail;
	@Setter @Getter private Integer total;
	
	@Setter @Getter
	public static class Store {
		private Long id;
		private String name;
	}
}
