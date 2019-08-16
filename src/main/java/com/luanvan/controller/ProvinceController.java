package com.luanvan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.Province;
import com.luanvan.service.ProvinceService;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {
	private ProvinceService provinceService;
	
	@Autowired
	public ProvinceController (ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
	@GetMapping
	public ResponseEntity<List<Province>> findAllProvince(){
		List<Province> provinces = provinceService.findAllProvince();
        if (provinces.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(provinces, HttpStatus.OK);
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Province> getProvinceById(@PathVariable("id") String id){
		
		Optional<Province> province = provinceService.findProvinceById(id);
		
		if(!province.isPresent()) {
			return new ResponseEntity<>(province.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(province.get(), HttpStatus.OK);
	}
	
}
