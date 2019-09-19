package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.request.OrderDTO;
import com.luanvan.model.Order;

public interface OrderService {
	
	//thêm hóa đơn
	Order save(OrderDTO req);
	
	List<Order> findByStoreId(Long storeId);
}
