package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.OrderDetailStatus;

@Repository
public interface OrderDetailStatusRepository extends JpaRepository<OrderDetailStatus, Long>{

}
