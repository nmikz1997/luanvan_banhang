package com.luanvan.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	
	//tim list promotion theo product trong thoi gian khuyen mai
	//List<Product> findByPromotionsDayStartBeforeAndPromotionsDayEndAfter(Date today,Date today);
	List<Promotion> findByProductsIdAndDayStartBeforeAndDayEndAfter(Long productId,Date day1,Date day2);
	
}
