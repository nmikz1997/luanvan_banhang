package com.luanvan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.District;
import com.luanvan.service.DistrictService;

@RestController
@RequestMapping("districts")
public class DistrictController {
	private DistrictService districtService;
	
	@Autowired
	public DistrictController (DistrictService districtService) {
		this.districtService = districtService;
	}
	
//	@GetMapping
//	public ResponseEntity<List<District>> findAllDistrict(){
//		List<District> districts = districtService.findAllDistrict();
//		
//		if(districts.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(districts, HttpStatus.OK);
//	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<District> getDistrictById(@PathVariable("id") String id){
		Optional<District> district = districtService.findDistrictById(id);
		
		if(!district.isPresent()) {
			return new ResponseEntity<>(district.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(district.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/findby")
	public ResponseEntity<List<District>> findByProvince(@RequestParam("provinceid") String provinceid) {
		List<District> districts = districtService.getByProvince(provinceid);
		
		if(districts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(districts, HttpStatus.OK);
	}
	
}
