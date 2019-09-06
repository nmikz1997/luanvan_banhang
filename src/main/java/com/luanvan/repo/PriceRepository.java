package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>{
	//tim don gia theo san pham
	List<Price> findPriceByProductId(Long id);
	
	//Tim don gia moi nhat theo san pham
	Price findTopPriceByProductIdOrderByIdDesc(Long id);
}
