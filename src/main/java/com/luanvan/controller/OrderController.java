package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.request.OrderDTO;
import com.luanvan.model.Order;
import com.luanvan.service.OrderDetailService;
import com.luanvan.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {
	
	private OrderService orderService;
	private OrderDetailService orderDetailService;
	
	@Autowired
	public OrderController(OrderService orderService,OrderDetailService orderDetailService) {
		this.orderService = orderService;
		this.orderDetailService = orderDetailService;
	}
	
	@PostMapping
	public void save(@RequestBody OrderDTO req) {
		orderService.save(req);
	}
	
	@GetMapping
	public List<Order> findByStore(){
		return null;
	}
	
	
}
