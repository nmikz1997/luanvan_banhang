package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Customer;

public interface CustomerService {
	
	List<Customer> findAll();
	
	List<Customer> findByName(String name);
	
	Customer findById(Long id);
	
	Customer create(Customer customer);

	Customer update(Customer customer, Long id);
	
	void delete(Long id);
}
