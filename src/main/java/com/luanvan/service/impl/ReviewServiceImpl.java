package com.luanvan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Customer;
import com.luanvan.model.Product;
import com.luanvan.model.Review;
import com.luanvan.repo.CustomerRepository;
import com.luanvan.repo.ProductRepository;
import com.luanvan.repo.ReviewRepository;
import com.luanvan.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private CustomerRepository customerRepository;
	private ProductRepository productRepository;
	
	@Autowired
	public ReviewServiceImpl(ReviewRepository reviewRepository,CustomerRepository customerRepository,
			ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
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
	@Transactional
	public Review save(Review review,CustomUserDetails user) {
		Customer customer = customerRepository.getOne(user.getCustomerId());
		
		review.setCustomer(customer);
		
		Review obj = reviewRepository.save(review);
		float avgstar = reviewRepository.avg(review.getProduct().getId());
		Product product = productRepository.getOne(review.getProduct().getId());
		product.setAvgStar(avgstar);
		productRepository.save(product);
		return obj;
	}

	@Override
	public Review getReview(Long productId, Long orderId,@AuthenticationPrincipal CustomUserDetails user) {
		return reviewRepository.getReview(user.getCustomerId(), productId, orderId)
				.orElseThrow(NotFoundException::new);
	}

}
