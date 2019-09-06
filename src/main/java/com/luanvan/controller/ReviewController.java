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

import com.luanvan.model.Review;
import com.luanvan.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	private ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("product/{productId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Review> findByProductId(@PathVariable Long productId){
		return reviewService.findReviewByProductId(productId);
	}
	
	@GetMapping("customer/{customerId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Review> findByCustomerId(@PathVariable Long customerId){
		return reviewService.findReviewByCustomerId(customerId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Review save(@RequestBody Review Review) {
		return reviewService.save(Review);
	}
}
