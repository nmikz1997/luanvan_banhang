package com.luanvan.controller;

import java.io.IOException;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanvan.dto.request.CreateGroupOrder;
import com.luanvan.dto.request.CreateOrderDTO;
import com.luanvan.dto.response.ChartOrderInf;
import com.luanvan.dto.response.Item;
import com.luanvan.dto.response.ListExchange;
import com.luanvan.dto.response.OrderDTO;
import com.luanvan.dto.response.OrderGroupCustomerDTO;
import com.luanvan.dto.response.OrderGroupDTO;
import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.OrderStatus;
import com.luanvan.service.OrderDetailService;
import com.luanvan.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {
	
	private OrderService orderService;
	private OrderDetailService orderDetailService;
	//static final String URL_EMPLOYEES = "https://fcsapi.com/api/forex/converter?pair1=VND&pair2=USD&amount=";
	//static final String access_key = "&access_key=mObVXMNtWVlH8iXjSA6apwKw8ufNsbxIQ7f3zF3myzATXPAPQl";
	static final String dongABank = "http://www.dongabank.com.vn/exchange/export";
	
	@Autowired
	public OrderController(OrderService orderService,OrderDetailService orderDetailService) {
		this.orderService = orderService;
		this.orderDetailService = orderDetailService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> save(@RequestBody List<CreateOrderDTO> req,Authentication auth) throws Exception {
		return orderService.save(req,auth);
	}
	
	
	@PostMapping("v2")
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> save(@RequestBody CreateGroupOrder req,Authentication auth) throws Exception {
		return orderService.save(req,auth);
	}
	
	@GetMapping
	public List<OrderDTO> findByStore(@AuthenticationPrincipal CustomUserDetails user){
		return orderService.findByStoreId(user.getStoreId());
	}
	
//	@GetMapping()
//	public List<Order> findAll(){
//		return orderService.findAll();
//	}
	
	@PostAuthorize("returnObject.customer.id == authentication.principal.customerId "
			+ "or returnObject.store.id == authentication.principal.storeId")
	@GetMapping("order-detail/{id}")
	public OrderDTO findOrderById(@PathVariable Long id){
		return orderService.findOrderById(id);
	}
	
	@PutMapping("order-detail/{id}")
	public void updateStatusOrder(@PathVariable Long id, @RequestBody OrderStatus orderStatus){
		orderService.updateStatusOrder(id,orderStatus);
	}
	
	@GetMapping("quan-ly-don-hang")
	public List<OrderGroupDTO> findByCustomer(Authentication auth) {
		return orderService.findByCustomer(auth);
	}
	
	
	@PostAuthorize("returnObject.customer.id == authentication.principal.customerId")
	@GetMapping("order-group/{id}")
	public OrderGroupCustomerDTO findByOrderGroup(@PathVariable Long id){
		return orderService.findByOrderGroup(id);
	}
	
	@GetMapping("store/{storeId}/{year}")
	public List<Entry<YearMonth, Integer>> chartOrder(@PathVariable Long storeId, @PathVariable int year){
		return orderService.chartByStoreId(storeId,year);
	}
	
	@GetMapping("store/{storeId}/{year}/{month}")
	public List<Entry<MonthDay, Integer>> chartOrder(@PathVariable Long storeId, @PathVariable int year, @PathVariable int month){
		return orderService.chartByStoreIdForMonth(storeId,year,month);
	}
	@GetMapping("chart-circle")
	public List<ChartOrderInf> chartCircle(@AuthenticationPrincipal CustomUserDetails user) {
		return orderService.chartCircle(user.getStoreId());
	}
	
	@DeleteMapping("khach-hang-huy/{groupId}")
	public void customerDelete(@PathVariable Long groupId, @AuthenticationPrincipal CustomUserDetails customer) {
		Long customerId = customer.getCustomerId();
		orderService.deleteGroup(groupId,customerId);
	}
	
	@GetMapping("exchange")
	public Item tyGiaNgoaiTe() throws IOException{
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(dongABank, String.class);
		response = response.replace( "(" , "").replace( ")", "");
		ObjectMapper mapper = new ObjectMapper();
		ListExchange listConverted = mapper.readValue(response,ListExchange.class);
		
		for (Item item : listConverted.getItems()) {
			if(item.getType().equals("USD")) {
				return item;
			}
		}
		return null;
	}
}
