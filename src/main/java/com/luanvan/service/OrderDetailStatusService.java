package com.luanvan.service;

import java.util.List;

import com.luanvan.model.OrderDetailStatus;

public interface OrderDetailStatusService {
	
	List<OrderDetailStatus> findAll();
	
	List<OrderDetailStatus> findByName(String name);
	
	OrderDetailStatus findById(Long id);
	
	OrderDetailStatus create(OrderDetailStatus orderDetailStatus);

	OrderDetailStatus update(OrderDetailStatus orderDetailStatus, Long id);
	
	void delete(Long id);
}
