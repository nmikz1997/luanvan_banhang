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

import com.luanvan.model.Question;
import com.luanvan.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	private QuestionService QuestionService;
	
	@Autowired
	public QuestionController(QuestionService QuestionService) {
		this.QuestionService = QuestionService;
	}
	
	@GetMapping("{id}")
	public Question findById(@PathVariable Long id) {
		return QuestionService.findById(id);
	}
	
	@GetMapping("product/{id}")
	public List<Question> findByProductId(@PathVariable Long id){
		return QuestionService.findByProductId(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Question save(@RequestBody Question Question) {
		return QuestionService.save(Question);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		QuestionService.delete(id);
	}
}
