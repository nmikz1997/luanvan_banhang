package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	Store findByUserEmail(String email);
	
	List<Store> findByStatus(int status);
}