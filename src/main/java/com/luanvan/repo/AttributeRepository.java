package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luanvan.model.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
	//tim attribute theo loai san pham
	List<Attribute> findByCategoriesId(Long CategoryId);
	//List<Attribute> findByCategory(Category category);
}
