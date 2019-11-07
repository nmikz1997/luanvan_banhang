package com.luanvan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.request.CreateRegisterStoreDTO;
import com.luanvan.dto.request.RegisterDTO;
import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Customer;
import com.luanvan.model.Store;
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
	
	@GetMapping("customer/thong-tin")
	public Customer customerInfo(Authentication auth) {
		return userService.getCustomer(auth);	
	}
	
	@GetMapping("store/thong-tin")
	public Store storeInfo(Authentication auth) {
		return userService.getStore(auth);
	}
	
	@GetMapping("testAuth")
	public Authentication getUser(Authentication auth) {
		return auth;
	}
	
	@GetMapping("getThoiHan")
	public Long getThoiHan(@AuthenticationPrincipal CustomUserDetails userDetail) {
		if( userDetail.getAuthorities().contains( new SimpleGrantedAuthority("ROLE_STORE")) &&
				!userDetail.getAuthorities().contains( new SimpleGrantedAuthority("ROLE_ADMIN")) ) {
			return userDetail.getHethan().getTime();
		}
		return null;
	}
}
