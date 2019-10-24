package com.luanvan.service;

import java.time.MonthDay;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.security.core.Authentication;

import com.luanvan.dto.request.CreateGroupOrder;
import com.luanvan.dto.request.CreateOrderDTO;
import com.luanvan.dto.response.ChartOrderInf;
import com.luanvan.dto.response.ChartOrderStore;
import com.luanvan.dto.response.OrderDTO;
import com.luanvan.dto.response.OrderGroupCustomerDTO;
import com.luanvan.dto.response.OrderGroupDTO;
import com.luanvan.model.Order;
import com.luanvan.model.OrderStatus;

public interface OrderService {
	
	List<OrderDTO> findByStoreId(Long storeId);
	

	List<Order> findAll();

	Map<String, String> save(List<CreateOrderDTO> req,Authentication auth) throws Exception;
	
	Map<String, String> save(CreateGroupOrder req,Authentication auth) throws Exception;
	
	OrderDTO findOrderById(Long id);

	void updateStatusOrder(Long id, OrderStatus orderStatus);
	
	List<OrderGroupDTO> findByCustomer(Authentication auth);
	
	OrderGroupCustomerDTO findByOrderGroup(Long id);

	List<Entry<YearMonth, Integer>> chartByStoreId(Long storeId, int year);

	List<Entry<MonthDay, Integer>> chartByStoreIdForMonth(Long storeId, int year, int month);
	
	//Map<YearMonth, Integer> chartByStoreId(Long storeId, int year);
	
	List<ChartOrderInf> chartCircle(Long storeId);

	void deleteGroup(Long groupId, Long customerId);
}
