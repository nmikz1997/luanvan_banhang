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

import com.luanvan.model.Material;
import com.luanvan.service.MaterialService;

@RestController
@RequestMapping("materials")
public class MaterialController {
	private MaterialService materialService;
	
	@Autowired
	public MaterialController (MaterialService materialService) {
		this.materialService = materialService;
	}
	
	@GetMapping
	public List<Material> findAll() {
		return materialService.findAll();
	}
	
	@GetMapping("{id}")
	public Material show(@PathVariable(name = "id") Long id) {
		return materialService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Material create(@Valid @RequestBody Material material){
		return materialService.create(material);
	}

	@PutMapping("/{id}")
	public Material update(@PathVariable Long id, @Valid @RequestBody Material material) {
		return materialService.update(material, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		materialService.delete(id);
	}
}
