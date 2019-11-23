package com.luanvan.pageController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luanvan.service.OrderService;

@Controller
@RequestMapping("store")
public class StorePage {
	
	private OrderService orderService;
	
	@Autowired
	public StorePage(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("product")
	public String product() {
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
	
	@GetMapping("inventory")
	public String inventory() {
		return "admin/inventory/inventory";
	}
	
	@GetMapping("dashboard")
	public String dashboard() {
		return "admin/dashboard/dashboard";
	}
	
	@GetMapping("thong-ke")
	public String thongKe() {
		return "admin/index/index";
	}
	
	@GetMapping("order-group/{id}")
	public String orderTemplate(@PathVariable Long id,Model model){
		model.addAttribute("data", orderService.findByOrderGroup(id) );
		return "homepage/emailTemplate";
	}
}
