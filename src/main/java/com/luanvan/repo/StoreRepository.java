package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luanvan.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{
	
}