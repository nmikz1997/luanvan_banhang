package com.luanvan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.dto.request.OrderDTO;
import com.luanvan.model.Order;
import com.luanvan.model.OrderDetail;
import com.luanvan.repo.OrderDetailRepository;
import com.luanvan.repo.OrderRepository;
import com.luanvan.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderRepository orderRepository;
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository,OrderDetailRepository orderDetailRepository) {
		this.orderRepository = orderRepository;
		this.orderDetailRepository = orderDetailRepository;
	}
	
	@Override
	@Transactional
	public Order save(OrderDTO req) {
		ModelMapper modelMapper = new ModelMapper();
		Order order = modelMapper.map(req, Order.class);
		
		Long orderId = orderRepository.save(order).getId();
		
		for( OrderDetail detail : order.getOrdersDetail() ) {
			detail.getId().setOrderId(orderId);
			orderDetailRepository.save(detail);
		}
		return orderRepository.save(order);
	}

	@Override
	public List<Order> findByStoreId(Long storeId) {
		return orderRepository.findAll();
	}

}
