package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	List<Review> findReviewByProductId(Long productId);
	
	@Query(value = "SELECT * FROM review WHERE customer_id = ?1", nativeQuery = true)
	List<Review> findReviewByCustomerId(Long customerId);
	
	
	
}
