package com.luanvan.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.luanvan.model.Customer;

public interface CustomerService {
	
	@Secured("ROLE_ADMIN")
	List<Customer> findAll();
	
	List<Customer> findByName(String name);
	
	Customer findById(Long id);
	
	Customer create(Customer customer);

	Customer update(Customer customer, Long id);
	
	void delete(Long id);
}
