package com.luanvan.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminPageController {
	
	@GetMapping("/")
	public String index() {
		return "admin/index";
	}
	
	@GetMapping("category")
	public String category() {
		return "admin/category/category";
	}
	
	@GetMapping("component")
	public String component() {
		return "admin/component";
	}
	
	
	@GetMapping("material")
	public String material() {
		return "admin/material/material";
	}
	
	
	@GetMapping("origin")
	public String origin() {
		return "admin/origin/orgirin";
	}
	
	@GetMapping("store")
	public String store() {
		return "admin/store/store";
	}
	
	@GetMapping("member-type")
	public String memberType() {
		return "admin/member-type/member-type";
	}
	
	@GetMapping("producer")
	public String producer() {
		return "admin/producer/producer";
	}
	
	@GetMapping("product-of-store")
	public String productOfStore() {
		return "admin/product/product-admin";
	}
	
}
