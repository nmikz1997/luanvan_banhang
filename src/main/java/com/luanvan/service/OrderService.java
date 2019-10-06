package com.luanvan.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;

import com.luanvan.dto.request.CreateOrderDTO;
import com.luanvan.dto.response.OrderCustomerDTO;
import com.luanvan.dto.response.OrderDTO;
import com.luanvan.model.Order;
import com.luanvan.model.OrderStatus;

public interface OrderService {
	
	List<Order> findByStoreId(Long storeId);

	List<Order> findAll();

	Map<String, String> save(List<CreateOrderDTO> req,Authentication auth) throws Exception;
	
	OrderDTO findOrderById(Long id);

	void updateStatusOrder(Long id, OrderStatus orderStatus);
	
	List<OrderCustomerDTO> findByCustomer(Authentication auth);
}
