package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	List<Inventory> findByProductId(Long productId);
	
}
