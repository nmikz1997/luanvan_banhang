package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Customer;
import com.luanvan.repo.CustomerRepository;
import com.luanvan.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerRepository CustomerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository CustomerRepository) {
		this.CustomerRepository = CustomerRepository;
	}
	
	@Override
	public List<Customer> findAll() {
		return CustomerRepository.findAll();
	}

	@Override
	public List<Customer> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findById(Long id) {
		return CustomerRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public Customer create(Customer Customer) {
		return CustomerRepository.save(Customer);
	}

	@Override
	public Customer update(Customer Customer, Long id) {
		CustomerRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return CustomerRepository.save(Customer);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
