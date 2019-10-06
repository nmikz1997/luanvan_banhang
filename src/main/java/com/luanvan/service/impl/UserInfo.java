package com.luanvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.luanvan.model.Customer;
import com.luanvan.model.Store;
import com.luanvan.repo.CustomerRepository;
import com.luanvan.repo.StoreRepository;

public class UserInfo {
	
	private Authentication auth;
	private StoreRepository storeRepo;
	private CustomerRepository customerRepo;
	
	@Autowired
	public UserInfo(Authentication auth, StoreRepository storeRepo, CustomerRepository customerRepo) {
		this.auth = auth;
		this.storeRepo = storeRepo;
		this.customerRepo = customerRepo;
	}
	
	public Customer currentCustomer() {
		return customerRepo.findByUserEmail(auth.getName());
	}
	
	public Store currentStore() {
		return storeRepo.findByUserEmail(auth.getName());
	}
	
	//goi 2 repo la store va customer
	
	//get store

	//get customer
}
