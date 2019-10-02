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
public class CreateOrderDTO {
	
	//@Setter @Getter private Long id;
	@Setter @Getter private String address;
	@Setter @Getter private Customer customer;
	@Setter @Getter private PaymentType paymentType;
	@Setter @Getter private Store store;
	@Setter @Getter private List<CreateOrderDetailDTO> ordersDetailDTO;
	
}
