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

import com.luanvan.model.Producer;
import com.luanvan.service.ProducerService;

@RestController
@RequestMapping("producers")
public class ProducerController {
	private ProducerService producerService;
	
	@Autowired
	public ProducerController (ProducerService producerService) {
		this.producerService = producerService;
	}
	
	@GetMapping
	public List<Producer> findAll() {
		return producerService.findAll();
	}
	
	@GetMapping("{id}")
	public Producer show(@PathVariable(name = "id") Long id) {
		return producerService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Producer create(@Valid @RequestBody Producer producer){
		return producerService.create(producer);
	}

	@PutMapping("/{id}")
	public Producer update(@PathVariable Long id, @Valid @RequestBody Producer producer) {
		return producerService.update(producer, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		producerService.delete(id);
	}
}
