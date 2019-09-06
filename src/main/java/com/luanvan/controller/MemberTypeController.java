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

import com.luanvan.model.MemberType;
import com.luanvan.service.MemberTypeService;

@RestController
@RequestMapping("members-type")
public class MemberTypeController {
	private MemberTypeService MemberTypeService;
	
	@Autowired
	public MemberTypeController (MemberTypeService MemberTypeService) {
		this.MemberTypeService = MemberTypeService;
	}
	
	@GetMapping
	public List<MemberType> findAll() {
		return MemberTypeService.findAll();
	}
	
	@GetMapping("{id}")
	public MemberType show(@PathVariable(name = "id") Long id) {
		return MemberTypeService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MemberType create(@Valid @RequestBody MemberType MemberType){
		return MemberTypeService.create(MemberType);
	}

	@PutMapping("/{id}")
	public MemberType update(@PathVariable Long id, @Valid @RequestBody MemberType MemberType) {
		return MemberTypeService.update(MemberType, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		MemberTypeService.delete(id);
	}
}
