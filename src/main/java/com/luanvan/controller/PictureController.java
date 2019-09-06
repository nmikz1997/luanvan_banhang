package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.Picture;
import com.luanvan.service.PictureService;

@RestController
@RequestMapping("/pictures")
public class PictureController {
	
	private PictureService pictureService;
	
	@Autowired
	public PictureController(PictureService pictureService) {
		this.pictureService = pictureService;
	}
	
	@GetMapping("{id}")
	public Picture findById(@PathVariable Long id) {
		return pictureService.findById(id);
	}
	
	@GetMapping("product/{id}")
	public List<Picture> findByProductId(@PathVariable Long id){
		return pictureService.findByProductId(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Picture save(@RequestBody Picture picture) {
		return pictureService.save(picture);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		pictureService.delete(id);
	}
}
