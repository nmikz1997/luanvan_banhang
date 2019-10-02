package com.luanvan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.luanvan.dto.request.CreateOrderDTO;
import com.luanvan.dto.response.OrderDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.exception.NotFoundException;
import com.luanvan.exception.RollbackException;
import com.luanvan.model.Order;
import com.luanvan.model.OrderDetail;
import com.luanvan.model.OrderStatus;
import com.luanvan.repo.CustomerRepository;
import com.luanvan.repo.OrderDetailRepository;
import com.luanvan.repo.OrderRepository;
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
	private OrderStatus status = new OrderStatus();
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository,
			OrderDetailRepository orderDetailRepository,
			StoreRepository storeRepository,
			CustomerRepository customerRepository,
			ProductRepository productRepository) {
		this.orderRepository 		= orderRepository;
		this.orderDetailRepository 	= orderDetailRepository;
		this.customerRepository 	= customerRepository;
		this.storeRepository		= storeRepository;
		this.productRepository		= productRepository;
	}
	
	@Override
	@Transactional(rollbackOn=RollbackException.class)
	public synchronized Map<String, String> save(List<CreateOrderDTO> req) throws Exception {
		Map<String, String> message = new HashMap<String, String>();
		ModelMapper modelMapper = new ModelMapper();
		List<Order> orders = modelMapper.map(req,new TypeToken<List<Order>>(){}.getType());
		for(Order order : orders) {
			status.setId((long)1);
			order.setOrderStatus(status);
			Long orderId = orderRepository.save(order).getId();
			for( OrderDetail detail : order.getOrdersDetail() ){
				
				ProductDTO productDTO = modelMapper.map(productRepository.getOne(detail.getProduct().getId()), ProductDTO.class);
				if(productDTO.getQuantity() - (productDTO.getSold() + detail.getQuantity()) < 0) {
					throw new RollbackException("Số lượng không đủ để đáp ứng");
				}
				
				detail.getId().setOrderId(orderId);
				orderDetailRepository.save(detail);
				
				message.put("Success", "Thành công");
			}
		}
		return message;
	}

	@Override
	public List<Order> findByStoreId(Long storeId) {
		return orderRepository.findAll();
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
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
	}

}
