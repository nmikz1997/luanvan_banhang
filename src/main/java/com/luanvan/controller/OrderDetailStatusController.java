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

import com.luanvan.model.OrderDetailStatus;
import com.luanvan.service.OrderDetailStatusService;

@RestController
@RequestMapping("order-detail-statuses")
public class OrderDetailStatusController {
	private OrderDetailStatusService orderDetailStatusService;
	
	@Autowired
	public OrderDetailStatusController (OrderDetailStatusService orderDetailStatusService) {
		this.orderDetailStatusService = orderDetailStatusService;
	}
	
	@GetMapping
	public List<OrderDetailStatus> findAll() {
		return orderDetailStatusService.findAll();
	}
	
	@GetMapping("{id}")
	public OrderDetailStatus show(@PathVariable(name = "id") Long id) {
		return orderDetailStatusService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderDetailStatus create(@Valid @RequestBody OrderDetailStatus origin){
		return orderDetailStatusService.create(origin);
	}

	@PutMapping("/{id}")
	public OrderDetailStatus update(@PathVariable Long id, @Valid @RequestBody OrderDetailStatus origin) {
		return orderDetailStatusService.update(origin, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		orderDetailStatusService.delete(id);
	}
}
