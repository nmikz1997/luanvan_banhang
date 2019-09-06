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

import com.luanvan.model.Origin;
import com.luanvan.service.OriginService;

@RestController
@RequestMapping("origins")
public class OriginController {
	private OriginService originService;
	
	@Autowired
	public OriginController (OriginService originService) {
		this.originService = originService;
	}
	
	@GetMapping
	public List<Origin> findAll() {
		return originService.findAll();
	}
	
	@GetMapping("{id}")
	public Origin show(@PathVariable(name = "id") Long id) {
		return originService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Origin create(@Valid @RequestBody Origin origin){
		return originService.create(origin);
	}

	@PutMapping("/{id}")
	public Origin update(@PathVariable Long id, @Valid @RequestBody Origin origin) {
		return originService.update(origin, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		originService.delete(id);
	}
}
