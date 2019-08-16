package com.luanvan.service;

import java.util.List;
import java.util.Optional;

import com.luanvan.model.District;

public interface DistrictService {
	//List<District> findAllDistrict();
	Optional<District> findDistrictById(String id);
	List<District> getByProvince(String provinceid);
}