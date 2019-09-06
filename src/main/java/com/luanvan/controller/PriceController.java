package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.Price;
import com.luanvan.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {
	
	private PriceService PriceService;
	
	@Autowired
	public PriceController(PriceService PriceService) {
		this.PriceService = PriceService;
	}
	
	@GetMapping("product/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Price> findByProduct(@PathVariable Long id){
		return PriceService.findByProduct(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Price save(@RequestBody Price Price) {
		return PriceService.save(Price);
	}
	
}
