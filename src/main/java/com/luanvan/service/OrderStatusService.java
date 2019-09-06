package com.luanvan.service;

import java.util.List;

import com.luanvan.model.OrderStatus;

public interface OrderStatusService {
	
	List<OrderStatus> findAll();
	
	List<OrderStatus> findByName(String name);
	
	OrderStatus findById(Long id);
	
	OrderStatus create(OrderStatus orderStatus);

	OrderStatus update(OrderStatus orderStatus, Long id);
	
	void delete(Long id);
}
