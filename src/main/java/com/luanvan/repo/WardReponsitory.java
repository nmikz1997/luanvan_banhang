package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Ward;

@Repository
public interface WardReponsitory extends JpaRepository<Ward, String>{
	List<Ward> findBydistrictid(String districtid);
}
