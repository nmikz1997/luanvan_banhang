package com.luanvan.service;

import java.util.List;
import java.util.Optional;

import com.luanvan.model.Province;

public interface ProvinceService {
	List<Province> findAllProvince();
	Optional<Province> findProvinceById(String id);
}