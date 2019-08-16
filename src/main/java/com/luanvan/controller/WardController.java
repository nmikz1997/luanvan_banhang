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

import com.luanvan.model.Ward;
import com.luanvan.service.WardService;

@RestController
@RequestMapping("wards")
public class WardController {
	private WardService wardService;
	
	@Autowired
	public WardController (WardService wardService) {
		this.wardService = wardService;
	}
	
//	@GetMapping
//	public ResponseEntity<List<Ward>> findAllDistrict(){
//		List<Ward> wards = wardService.findAllWard();
//		
//		if(wards.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(wards, HttpStatus.OK);
//	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Ward> getWardById(@PathVariable("id") String id){
		Optional<Ward> ward = wardService.findWardById(id);
		
		if(!ward.isPresent()) {
			return new ResponseEntity<>(ward.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ward.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/findbydistrict")
	public ResponseEntity<List<Ward>> findByDistrict(@RequestParam("districtid") String districtid) {
		List<Ward> wards = wardService.getByDistrict(districtid);
		
		if(wards.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(wards, HttpStatus.OK);
	}
	
}
