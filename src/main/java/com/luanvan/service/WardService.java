package com.luanvan.service;

import java.util.List;
import java.util.Optional;

import com.luanvan.model.Ward;

public interface WardService {
	//List<Ward> findAllWard();
	Optional<Ward> findWardById(String id);
	List<Ward> getByDistrict(String districtid);
}