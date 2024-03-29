package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Origin;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long>{
	
}
