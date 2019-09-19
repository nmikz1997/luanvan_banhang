package com.luanvan.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.response.PromotionDTO;
import com.luanvan.model.Promotion;
import com.luanvan.repo.ProductRepository;
import com.luanvan.service.PromotionService;

@RestController
@RequestMapping("promotions")
public class PromotionController {
	private PromotionService promotionService;
	private ProductRepository productRepository;
	
	@Autowired
	public PromotionController (PromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	@GetMapping
	public List<Promotion> findAll() {
		return promotionService.findAll();
	}
	
	@GetMapping("{id}")
	public PromotionDTO show(@PathVariable(name = "id") Long id) {
		return promotionService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Promotion create(@RequestBody PromotionDTO req){
		
		ModelMapper modelMapper = new ModelMapper();
 		Promotion promotion = modelMapper.map(req, Promotion.class);
 		
		return promotionService.create(promotion);
	}
	
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Promotion create(@Valid @RequestBody CreatePromotionDTO promotionDTO){
//		//System.out.println(promotionDTO);
//		return promotionService.create(promotionDTO);
////		return null;
//	}

	@PutMapping("/{id}")
	public Promotion update(@PathVariable Long id, @Valid @RequestBody Promotion promotion) {
		return promotionService.update(promotion, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		promotionService.delete(id);
	}
}
