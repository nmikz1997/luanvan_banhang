package com.luanvan.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.luanvan.dto.request.CreateStoreDTO;
import com.luanvan.model.Store;
import com.luanvan.service.StoreService;

@RestController
@RequestMapping("stores")
public class StoreController {
	private StoreService StoreService;
	
	@Autowired
	public StoreController (StoreService StoreService) {
		this.StoreService = StoreService;
	}
	
	@GetMapping
	public List<Store> findAll() {
		return StoreService.findAll();
	}
	
	@GetMapping("{id}")
	public Store show(@PathVariable(name = "id") Long id) {
		return StoreService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody CreateStoreDTO store){
		StoreService.create(store);
	}

	@PutMapping("/{id}")
	public Store update(@PathVariable Long id, @Valid @RequestBody Store Store) {
		return StoreService.update(Store, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		StoreService.delete(id);
	}
}
