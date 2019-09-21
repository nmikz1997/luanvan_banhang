package com.luanvan.dto.request;

import java.util.List;

import com.luanvan.model.Customer;
import com.luanvan.model.PaymentType;
import com.luanvan.model.Store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	@Setter @Getter private Long id;
	@Setter @Getter private String address;
	private int total;
	@Setter @Getter private Customer customer;
	@Setter @Getter private PaymentType paymentType;
	@Setter @Getter private Store store;
	@Setter @Getter private List<OrderDetailDTO> ordersDetailDTO;

	public int getTotal() {
		int total = 0;
		for(OrderDetailDTO detail : ordersDetailDTO) {
			total += detail.getAmount();
		}
		return this.total = total;
	}
	
	
	
}
