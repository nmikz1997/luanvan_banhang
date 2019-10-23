package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.service.OrderDetailService;
import com.luanvan.dto.response.ChartOrderInf;
import com.luanvan.model.CustomUserDetails;

@RestController
@RequestMapping("orders-details")
public class OrderDetailController {
	
	private OrderDetailService orderDetailService;

	@Autowired
	public OrderDetailController(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}
	
	@GetMapping("thong-ke/{year}")
	public List<ChartOrderInf> totalOfYear(@AuthenticationPrincipal CustomUserDetails store,@PathVariable int year){
		return orderDetailService.chartOfYear(store.getStoreId(), year);
	}
	
	@GetMapping("thong-ke/{year}/{month}")
	public List<ChartOrderInf> totalOfMonth(@AuthenticationPrincipal CustomUserDetails store,@PathVariable int year, @PathVariable int month){
		return orderDetailService.chartOfMonth(store.getStoreId(), year, month);
	}
	
	@GetMapping("tong-doanh-thu")
	public List<ChartOrderInf> tongDoanhThu(@AuthenticationPrincipal CustomUserDetails store){
		return orderDetailService.tongDoanhThu(store.getStoreId());
	}
	
}
