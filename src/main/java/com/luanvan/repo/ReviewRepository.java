package com.luanvan.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	List<Review> findReviewByProductId(Long productId);
	
	@Query(value = "SELECT * FROM review WHERE customer_id = ?1", nativeQuery = true)
	List<Review> findReviewByCustomerId(Long customerId);
	
	@Query(value = "SELECT * FROM review WHERE customer_id = ?1 AND product_id = ?2 AND order_id = ?3", nativeQuery = true)
	Optional<Review> getReview(Long customerId, Long productId, Long orderId);

	@Query(value = "SELECT avg(star) FROM review where product_id = ?1", nativeQuery=true)
	float avg(Long id);
	
}
