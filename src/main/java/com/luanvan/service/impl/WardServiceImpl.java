package com.luanvan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.Ward;
import com.luanvan.repo.WardReponsitory;
import com.luanvan.service.WardService;

@Service
public class WardServiceImpl implements WardService{
	
	private WardReponsitory wardRepository;
	
	@Autowired
	public WardServiceImpl (WardReponsitory wardRepository) {
		this.wardRepository = wardRepository;
	}

//	@Override
//	public List<Ward> findAllWard() {
//		return wardRepository.findAll();
//	}

	@Override
	public Optional<Ward> findWardById(String id) {
		return wardRepository.findById(id);
	}

	@Override
	public List<Ward> getByDistrict(String districtid) {
		return wardRepository.findBydistrictid(districtid);
	}
	

}
