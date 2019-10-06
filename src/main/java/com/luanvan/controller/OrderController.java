package com.luanvan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.request.CreateOrderDTO;
import com.luanvan.dto.response.OrderCustomerDTO;
import com.luanvan.dto.response.OrderDTO;
import com.luanvan.model.Order;
import com.luanvan.model.OrderStatus;
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
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> save(@RequestBody List<CreateOrderDTO> req,Authentication auth) throws Exception {
		return orderService.save(req,auth);
	}
	
	@GetMapping("store/{storeId}")
	public List<Order> findByStore(){
		return null;
	}
	
	@GetMapping()
	public List<Order> findAll(){
		return orderService.findAll();
	}
	
	@PostAuthorize("returnObject.customer.id == authentication.principal.customerId "
			+ "or returnObject.store.id == authentication.principal.storeId")
	@GetMapping("order-detail/{id}")
	public OrderDTO findOrderById(@PathVariable Long id){
		return orderService.findOrderById(id);
	}
	
	@PutMapping("order-detail/{id}")
	public void updateStatusOrder(@PathVariable Long id, @RequestBody OrderStatus orderStatus){
		orderService.updateStatusOrder(id,orderStatus);
	}
	
	@GetMapping("quan-ly-don-hang")
	public List<OrderCustomerDTO> findByCustomer(Authentication auth) {
		return orderService.findByCustomer(auth);
	}
}
