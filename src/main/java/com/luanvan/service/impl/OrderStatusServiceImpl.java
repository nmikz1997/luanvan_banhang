package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.OrderStatus;
import com.luanvan.repo.OrderStatusRepository;
import com.luanvan.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{
	
	private OrderStatusRepository orderStatusRepository;
	
	@Autowired
	public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
		this.orderStatusRepository = orderStatusRepository;
	}
	
	@Override
	public List<OrderStatus> findAll() {
		return orderStatusRepository.findAll();
	}

	@Override
	public List<OrderStatus> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderStatus findById(Long id) {
		return orderStatusRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public OrderStatus create(OrderStatus orderStatus) {
		return orderStatusRepository.save(orderStatus);
	}

	@Override
	public OrderStatus update(OrderStatus orderStatus, Long id) {
		orderStatusRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return orderStatusRepository.save(orderStatus);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}
	
}
