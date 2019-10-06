package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.Review;
import com.luanvan.repo.ReviewRepository;
import com.luanvan.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepository;
	
	@Autowired
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Override
	public List<Review> findReviewByProductId(Long productId) {
		return reviewRepository.findReviewByProductId(productId);
	}

	@Override
	public List<Review> findReviewByCustomerId(Long customerId) {
		return reviewRepository.findReviewByCustomerId(customerId);
	}

	@Override
	public Review save(Review review) {
		return reviewRepository.save(review);
	}

}
