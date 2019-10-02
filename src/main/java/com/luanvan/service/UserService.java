package com.luanvan.service;

import com.luanvan.dto.request.CreateRegisterStoreDTO;
import com.luanvan.dto.request.RegisterDTO;
import com.luanvan.model.User;

public interface UserService {
	
	void save (RegisterDTO req);
	
	User findById(Long id);
	
	User findByEmail(String email);

	void RegisterStore(CreateRegisterStoreDTO req);
}
