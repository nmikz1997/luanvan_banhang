package com.luanvan.service;

import java.util.List;

import com.luanvan.model.MemberType;

public interface MemberTypeService {
	
	List<MemberType> findAll();
	
	List<MemberType> findByName(String name);
	
	MemberType findById(Long id);
	
	MemberType create(MemberType memberType);

	MemberType update(MemberType memberType, Long id);
	
	void delete(Long id);
}
