package com.luanvan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.request.CreateRegisterStoreDTO;
import com.luanvan.dto.request.RegisterDTO;
import com.luanvan.service.UserService;

@RestController
@RequestMapping("users")
public class UserController{
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public void save(@Valid @RequestBody RegisterDTO req) {
		userService.save(req);
	}
	
	@PostMapping("dang-ky-ban-hang")
	public void registerStore(@Valid @RequestBody CreateRegisterStoreDTO req) {
		userService.RegisterStore(req);
	}
	
	@GetMapping("/ai")
	public Authentication currentUser(Authentication auth) {
		//UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return auth;
	}
}
