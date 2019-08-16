package com.luanvan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.Province;
import com.luanvan.service.ProvinceService;
import com.luanvan.repo.ProvinceReponsitory;

@Service
public class ProvinceServiceImpl implements ProvinceService{
	private ProvinceReponsitory provinceRepository;
	
	@Autowired
	public ProvinceServiceImpl (ProvinceReponsitory provinceRepository) {
		this.provinceRepository = provinceRepository;
	}
	
	@Override
	public List<Province> findAllProvince() {
		// TODO Auto-generated method stub
		return provinceRepository.findAll();
	}

	@Override
	public Optional<Province> findProvinceById(String id) {
		// TODO Auto-generated method stub
		return provinceRepository.findById(id);
	}
	
}
