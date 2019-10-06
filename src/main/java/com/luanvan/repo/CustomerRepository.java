package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByUserEmail(String email);
	
}
