package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
