package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Province;

@Repository
public interface ProvinceReponsitory extends JpaRepository<Province, String>{

}
