package com.luanvan.service;

import java.util.List;

import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Review;

public interface ReviewService {
	
	//tim review theo san pham
	List<Review> findReviewByProductId(Long productId);
	
	//tim review theo khach hang
	List<Review> findReviewByCustomerId(Long customerId);
	
	//save review
	Review save(Review review, CustomUserDetails user);
	
	Review getReview(Long productId, Long orderId, CustomUserDetails user);
}
