package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.District;

@Repository
public interface DistrictReponsitory extends JpaRepository<District, String>{
	List<District> findByprovinceid(String provinceid);
}