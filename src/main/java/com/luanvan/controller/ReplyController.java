package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.Reply;
import com.luanvan.service.ReplyService;

@RestController
@RequestMapping("replies")
public class ReplyController {
	
	private ReplyService replyService;
	
	@Autowired
	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@GetMapping("{id}")
	public Reply findById(@PathVariable Long id) {
		return replyService.findById(id);
	}
	
	@GetMapping("question/{questionId}")
	public List<Reply> findByQuestion(@PathVariable Long questionId){
		return replyService.findByQuestion(questionId);
	}
	
	@PostMapping
	public Reply save(@RequestBody Reply reply){
		return replyService.save(reply);
	}
	
}
