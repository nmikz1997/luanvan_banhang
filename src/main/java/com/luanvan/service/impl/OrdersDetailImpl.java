package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.dto.response.ChartOrderInf;
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
	
	@Override
	public List<OrderDetail> orderDetails(Long storeId){
		return orderDetailRepository.findByOrderStoreId(storeId);
	}
	
	@Override
	public List<ChartOrderInf> chartOfMonth(Long storeId, int year, int month){
		return orderDetailRepository.chartOfMonth(storeId, year, month);
	}

	@Override
	public List<ChartOrderInf> chartOfYear(Long storeId, int year) {
		return orderDetailRepository.chartOfYear(storeId, year);
	}

	@Override
	public List<ChartOrderInf> tongDoanhThu(Long storeId) {
		return orderDetailRepository.tongDoanhThu(storeId);
	}
}
