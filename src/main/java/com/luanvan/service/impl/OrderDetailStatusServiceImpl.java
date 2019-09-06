package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.OrderDetailStatus;
import com.luanvan.repo.OrderDetailStatusRepository;
import com.luanvan.service.OrderDetailStatusService;

@Service
public class OrderDetailStatusServiceImpl implements OrderDetailStatusService{
	
	private OrderDetailStatusRepository orderDetailStatusRepository;
	
	@Autowired
	public OrderDetailStatusServiceImpl(OrderDetailStatusRepository orderDetailStatusRepository) {
		this.orderDetailStatusRepository = orderDetailStatusRepository;
	}
	
	@Override
	public List<OrderDetailStatus> findAll() {
		return orderDetailStatusRepository.findAll();
	}

	@Override
	public List<OrderDetailStatus> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetailStatus findById(Long id) {
		return orderDetailStatusRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public OrderDetailStatus create(OrderDetailStatus origin) {
		return orderDetailStatusRepository.save(origin);
	}

	@Override
	public OrderDetailStatus update(OrderDetailStatus origin, Long id) {
		orderDetailStatusRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return orderDetailStatusRepository.save(origin);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
