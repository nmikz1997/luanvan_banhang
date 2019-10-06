package com.luanvan.service;

import org.springframework.security.core.Authentication;

import com.luanvan.dto.request.CreateRegisterStoreDTO;
import com.luanvan.dto.request.RegisterDTO;
import com.luanvan.model.Customer;
import com.luanvan.model.Store;
import com.luanvan.model.User;

public interface UserService {
	
	void save (RegisterDTO req);
	
	User findById(Long id);
	
	User findByEmail(String email);

	void RegisterStore(CreateRegisterStoreDTO req);

	Customer getCustomer(Authentication auth);

	Store getStore(Authentication auth);
}
