package com.luanvan.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("store")
public class StorePage {
	
	@GetMapping("product")
	public String category() {
		return "admin/product/product";
	}
	
	@GetMapping("promotion")
	public String promotion() {
		return "admin/promotion/promotion";
	}
	
	@GetMapping("order")
	public String order() {
		return "admin/order/order";
	}
	
	@GetMapping("order-detail")
	public String orderDetail() {
		return "admin/orderDetail";
	}
	
	@GetMapping("question-management")
	public String questionManagement() {
		return "admin/question/question";
	}
	
	@GetMapping("dashboard")
	public String dashboard() {
		return "admin/dashboard";
	}
}
