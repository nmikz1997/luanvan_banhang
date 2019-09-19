package com.luanvan.service;

import com.luanvan.model.User;

public interface UserService {
	
	User findById(Long id);
	
	User findByEmail(String email);
}
