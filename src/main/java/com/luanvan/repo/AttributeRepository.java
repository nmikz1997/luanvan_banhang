package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luanvan.model.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
	//tim attribute theo loai san pham
}
