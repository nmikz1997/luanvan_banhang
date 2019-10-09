package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Customer;
import com.luanvan.model.OrderGroup;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long>{

	List<OrderGroup> findByCustomer(Customer customer);
	
}
