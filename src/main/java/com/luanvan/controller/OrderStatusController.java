package com.luanvan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.OrderStatus;
import com.luanvan.service.OrderStatusService;

@RestController
@RequestMapping("order-statuses")
public class OrderStatusController {
	private OrderStatusService orderStatusService;
	
	@Autowired
	public OrderStatusController (OrderStatusService orderStatusService) {
		this.orderStatusService = orderStatusService;
	}
	
	@GetMapping
	public List<OrderStatus> findAll() {
		return orderStatusService.findAll();
	}
	
	@GetMapping("{id}")
	public OrderStatus show(@PathVariable(name = "id") Long id) {
		return orderStatusService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderStatus create(@Valid @RequestBody OrderStatus origin){
		return orderStatusService.create(origin);
	}

	@PutMapping("/{id}")
	public OrderStatus update(@PathVariable Long id, @Valid @RequestBody OrderStatus origin) {
		return orderStatusService.update(origin, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		orderStatusService.delete(id);
	}
}
