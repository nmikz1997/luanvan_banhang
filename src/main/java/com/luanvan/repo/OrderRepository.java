package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Customer;
import com.luanvan.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByStoreId(Long storeId);
	
	List<Order> findByCustomer(Customer customer);
	
}
