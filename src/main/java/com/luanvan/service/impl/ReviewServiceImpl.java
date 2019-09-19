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
		//lấy ra số lượt đánh giá sản phẩm
		//điểm trung bình = (điểm hiện tại * số lượt đánh giá + điểm mới review.getStar() ) / (số lượt đánh giá + 1)
		//số lượt đánh giá sản phẩm +1
		return reviewRepository.save(review);
	}

}
