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

import com.luanvan.model.Customer;
import com.luanvan.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {
	private CustomerService CustomerService;
	
	@Autowired
	public CustomerController (CustomerService CustomerService) {
		this.CustomerService = CustomerService;
	}
	
	@GetMapping
	public List<Customer> findAll() {
		return CustomerService.findAll();
	}
	
	@GetMapping("{id}")
	public Customer show(@PathVariable(name = "id") Long id) {
		return CustomerService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer create(@Valid @RequestBody Customer Customer){
		return CustomerService.create(Customer);
	}

	@PutMapping("/{id}")
	public Customer update(@PathVariable Long id, @Valid @RequestBody Customer Customer) {
		return CustomerService.update(Customer, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		CustomerService.delete(id);
	}
	
	
}
