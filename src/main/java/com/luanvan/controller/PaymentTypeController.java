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

import com.luanvan.model.PaymentType;
import com.luanvan.service.PaymentTypeService;

@RestController
@RequestMapping("payments-type")
public class PaymentTypeController {
	private PaymentTypeService paymentTypeService;
	
	@Autowired
	public PaymentTypeController (PaymentTypeService paymentTypeService) {
		this.paymentTypeService = paymentTypeService;
	}
	
	@GetMapping
	public List<PaymentType> findAll() {
		return paymentTypeService.findAll();
	}
	
	@GetMapping("{id}")
	public PaymentType show(@PathVariable(name = "id") Long id) {
		return paymentTypeService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentType create(@Valid @RequestBody PaymentType paymentType){
		return paymentTypeService.create(paymentType);
	}

	@PutMapping("/{id}")
	public PaymentType update(@PathVariable Long id, @Valid @RequestBody PaymentType paymentType) {
		return paymentTypeService.update(paymentType, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		paymentTypeService.delete(id);
	}
}
