package com.luanvan.service.impl;

import java.time.MonthDay;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luanvan.dto.request.CreateGroupOrder;
import com.luanvan.dto.request.CreateOrderDTO;
import com.luanvan.dto.response.ChartOrderInf;
import com.luanvan.dto.response.ChartOrderStore;
import com.luanvan.dto.response.OrderDTO;
import com.luanvan.dto.response.OrderGroupCustomerDTO;
import com.luanvan.dto.response.OrderGroupDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.exception.NotFoundException;
import com.luanvan.exception.RollbackException;
import com.luanvan.model.Customer;
import com.luanvan.model.Order;
import com.luanvan.model.OrderDetail;
import com.luanvan.model.OrderGroup;
import com.luanvan.model.OrderStatus;
import com.luanvan.repo.CustomerRepository;
import com.luanvan.repo.OrderDetailRepository;
import com.luanvan.repo.OrderGroupRepository;
import com.luanvan.repo.OrderRepository;
import com.luanvan.repo.OrderStatusRepository;
import com.luanvan.repo.ProductRepository;
import com.luanvan.repo.StoreRepository;
import com.luanvan.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderRepository orderRepository;
	private OrderDetailRepository orderDetailRepository;
	private StoreRepository storeRepository;
	private CustomerRepository customerRepository;
	private ProductRepository productRepository;
	private OrderGroupRepository orderGroupRepository;
	private OrderStatusRepository orderStatus;
	private SendGridEmailService sendGridEmailService;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository,
			OrderDetailRepository orderDetailRepository,
			StoreRepository storeRepository,
			CustomerRepository customerRepository,
			ProductRepository productRepository,
			OrderGroupRepository orderGroupRepository,
			OrderStatusRepository orderStatus,
			SendGridEmailService sendGridEmailService) {
		this.orderRepository 		= orderRepository;
		this.orderDetailRepository 	= orderDetailRepository;
		this.customerRepository 	= customerRepository;
		this.storeRepository		= storeRepository;
		this.productRepository		= productRepository;
		this.orderGroupRepository	= orderGroupRepository;
		this.orderStatus			= orderStatus;
		this.sendGridEmailService 	= sendGridEmailService;
	}
	
	@Override
	@Transactional(rollbackOn=RollbackException.class)
	public synchronized Map<String, String> save(List<CreateOrderDTO> req, Authentication auth) throws Exception {
		Map<String, String> message = new HashMap<String, String>();
		ModelMapper modelMapper = new ModelMapper();
		
		List<Order> orders = modelMapper.map(req,new TypeToken<List<Order>>(){}.getType());
		
		Customer customer = customerRepository.findByUserEmail(auth.getName());
		OrderStatus status = new OrderStatus();
		status.setId((long)1);
		
		for(Order order : orders) {
			
			order.setOrderStatus( status );
			order.setCustomer( customer );
			Long orderId = orderRepository.save(order).getId();
			
			for( OrderDetail detail : order.getOrdersDetail() ){
				
				ProductDTO productDTO = modelMapper.map(productRepository.getOne(detail.getProduct().getId()), ProductDTO.class);
				if(productDTO.getQuantity() - (productDTO.getSold() + detail.getQuantity()) < 0) {
					throw new RollbackException("Số lượng không đủ để đáp ứng");
				}
				
				detail.getId().setOrderId(orderId);
				orderDetailRepository.save(detail);
				
			}
		}
		message.put("Success", "Thành công");
		return message;
	}
	
	@Override//ver2
	@Transactional(rollbackOn=RollbackException.class)
	public synchronized Map<String, String> save(CreateGroupOrder req, Authentication auth) throws Exception {
		Customer customer = customerRepository.findByUserEmail(auth.getName());
		OrderGroup orderGroup = req.getOrderGroup();
		orderGroup.setCustomer(customer);
		
		orderGroup = orderGroupRepository.save(orderGroup);
		Map<String, String> message = new HashMap<String, String>();
		ModelMapper modelMapper = new ModelMapper();
		
		List<Order> orders = modelMapper.map(req.getOrders(),new TypeToken<List<Order>>(){}.getType());
		
		OrderStatus status = new OrderStatus();
		status.setId((long)1);
		
		for(Order order : orders) {
			
			order.setOrderStatus( status );
			order.setCustomer( customer );
			order.setOrderGroup(orderGroup);
			Long orderId = orderRepository.save(order).getId();
			
			for( OrderDetail detail : order.getOrdersDetail() ){
				
				ProductDTO productDTO = modelMapper.map(productRepository.getOne(detail.getProduct().getId()), ProductDTO.class);
				if(productDTO.getQuantity() - (productDTO.getSold() + detail.getQuantity()) < 0) {
					throw new RollbackException("Số lượng không đủ để đáp ứng");
				}
				
				detail.getId().setOrderId(orderId);
				orderDetailRepository.save(detail);
			}
		}
		message.put("Success", "Thành công");
		return message;
	}

	@Override
	public List<OrderDTO> findByStoreId(Long storeId) {
		List<Order> orders = orderRepository.findByStoreId(storeId);
		
		ModelMapper modelMapper = new ModelMapper();
		List<OrderDTO> orderDTO = modelMapper.map(orders,new TypeToken<List<OrderDTO>>(){}.getType());
		
		return orderDTO;
	}

	@Override
	public List<Order> findAll() {
		List<Order> orders = orderRepository.findAll();
		return orders;
	}

	@Override
	public OrderDTO findOrderById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(NotFoundException::new);
		ModelMapper modelMapper = new ModelMapper();
		OrderDTO dto = modelMapper.map(order, OrderDTO.class);
		return dto;
	}

	@Override
	public void updateStatusOrder(Long id, OrderStatus orderStatus) {
		
		Order order = orderRepository.getOne(id);
		Long count = orderRepository.countByOrderGroupIdAndOrderStatusIdGreaterThan(order.getOrderGroup().getId(), (long) 1);
		if(orderStatus.getId() == 2 && count == (long) 0) {
			sendMail(order.getOrderGroup().getId(),order.getCustomer().getUser().getEmail());
		}
		
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
	}
	
	private void sendMail(Long groupId, String customerEmail) {
		RestTemplate restTemplate = new RestTemplate();
		String body = restTemplate.getForObject("http://localhost:8080/store/order-group/"+groupId, String.class);
		sendGridEmailService.sendHTML(customerEmail, "Xác nhận đơn hàng #"+groupId, body);
	}

	@Override
	public List<OrderGroupDTO> findByCustomer(Authentication auth) {
		
		Customer customer = customerRepository.findByUserEmail(auth.getName());
		
		List<OrderGroup> ordersGroup = orderGroupRepository.findByCustomer(customer);
		ModelMapper modelMapper = new ModelMapper();
		List<OrderGroupDTO> ordersGroupDTO = modelMapper.map(ordersGroup,new TypeToken<List<OrderGroupDTO>>(){}.getType());
		
		return ordersGroupDTO;
	}

	@Override
	public OrderGroupCustomerDTO findByOrderGroup(Long id) {
		OrderGroup orderGroup = orderGroupRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		
		ModelMapper modelMapper = new ModelMapper();
		OrderGroupCustomerDTO dto = modelMapper.map(orderGroup, OrderGroupCustomerDTO.class);

		return dto;
	}

	@Override
	public List<Entry<YearMonth, Integer>> chartByStoreId(Long storeId, int year) {
		List<Order> orders = orderRepository.findOrderOfYear(storeId,(long) 5, year);
		
		ModelMapper modelMapper = new ModelMapper();
		List<ChartOrderStore> orderDTO = modelMapper.map(orders,new TypeToken<List<ChartOrderStore>>(){}.getType());
		
		Map<YearMonth, Integer> caloriesByMonth = orderDTO.stream()
		        .collect(Collectors.groupingBy(o -> YearMonth.from( o.getCreatedAt() ),
		                                       TreeMap::new,
		                                       Collectors.summingInt( o -> o.getTotal() )));
		
		List<Entry<YearMonth, Integer>> rs = caloriesByMonth
				.entrySet()
				.stream()
				.collect(Collectors.toList());
		
		return rs;
	}

	@Override
	public List<Entry<MonthDay, Integer>> chartByStoreIdForMonth(Long storeId, int year, int month) {
		List<Order> orders = orderRepository.findOrderOfMonth(storeId,(long) 5, year, month);
		
		ModelMapper modelMapper = new ModelMapper();
		List<ChartOrderStore> orderDTO = modelMapper.map(orders,new TypeToken<List<ChartOrderStore>>(){}.getType());
		
		Map<MonthDay, Integer> caloriesByMonth = orderDTO.stream()
		        .collect(Collectors.groupingBy(o -> MonthDay.from( o.getCreatedAt() ),
		                                       TreeMap::new,
		                                       Collectors.summingInt( o -> o.getTotal() )));		
	
		List<Entry<MonthDay, Integer>> rs = caloriesByMonth
				.entrySet()
				.stream()
				.collect(Collectors.toList());
	
		return rs;
	}

	@Override
	public List<ChartOrderInf> chartCircle(Long storeId) {
		return orderRepository.chartCircle(storeId);
	}

	@Override
	@Transactional(rollbackOn=RollbackException.class)
	public void deleteGroup(Long groupId,Long customerId) {
		OrderGroup orderGroup = orderGroupRepository.findById(groupId).get();
		
		if(orderGroup.getCustomer().getId() != customerId) {
			throw new RollbackException("Không phải bạn");
		}else {
			List<Order> orders = orderRepository.findByOrderGroupId(groupId);
			OrderStatus status = orderStatus.getOne((long)6);
			
			orders.stream().forEach(order ->{
				order.setOrderStatus(status);
			});
			orderRepository.saveAll(orders);
		}
	}
}
