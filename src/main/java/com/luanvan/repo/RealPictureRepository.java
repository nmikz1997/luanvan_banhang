package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luanvan.model.Image360;

public interface RealPictureRepository extends JpaRepository<Image360, Long>{
	
	@Query(value = "SELECT * FROM image360 where product_id = :product order by status", nativeQuery=true)
	List<Image360> image360Product(@Param("product") Long product);
	
}
