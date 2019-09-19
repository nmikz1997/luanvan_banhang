package com.luanvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.OrderDetail;
import com.luanvan.repo.OrderDetailRepository;
import com.luanvan.service.OrderDetailService;

@Service
public class OrdersDetailImpl implements OrderDetailService{
	
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	public OrdersDetailImpl(OrderDetailRepository orderDetailRepository) {
		this.orderDetailRepository = orderDetailRepository;
	}
	
	@Override
	public void save(OrderDetail orderDetail) {
		orderDetailRepository.save(orderDetail);
	}

}
