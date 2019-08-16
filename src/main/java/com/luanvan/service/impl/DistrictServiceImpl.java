package com.luanvan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.District;
import com.luanvan.repo.DistrictReponsitory;
import com.luanvan.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService{
	
	private DistrictReponsitory districtRepository;
	
	@Autowired
	public DistrictServiceImpl (DistrictReponsitory districtRepository) {
		this.districtRepository = districtRepository;
	}
	
//	@Override
//	public List<District> findAllDistrict() {
//		return districtRepository.findAll();
//	}

	@Override
	public Optional<District> findDistrictById(String id) {
		return districtRepository.findById(id);
	}

	@Override
	public List<District> getByProvince(String provinceid) {
		return districtRepository.findByprovinceid(provinceid);
	}

}
