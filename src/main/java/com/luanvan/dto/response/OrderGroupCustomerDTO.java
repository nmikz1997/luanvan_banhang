package com.luanvan.dto.response;

import java.util.Date;
import java.util.List;

import com.luanvan.dto.response.OrderGroupDTO.Order;
import com.luanvan.model.Customer;
import com.luanvan.model.PaymentType;

import lombok.Getter;
import lombok.Setter;

public class OrderGroupCustomerDTO {
	@Setter @Getter private Long id;
	@Setter @Getter private String address;
	@Setter @Getter private Date createdAt;
	@Setter @Getter private PaymentType paymentType;
	@Setter @Getter private Customer customer;
	@Setter @Getter private List<OrderCustomerDTO> orders;
	
	public Integer getTotalGroup() {
		Integer totalGroup = 0;
		for(OrderCustomerDTO order : orders) {
			totalGroup += order.getTotal();
		}
		return totalGroup;
	}
	
}
