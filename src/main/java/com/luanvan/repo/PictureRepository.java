package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long>{
	
	Picture findByProductIdAndAvatar(Long productId,boolean avatar);
	
	@Query(value = "SELECT * FROM picture WHERE product_id = ?1", nativeQuery = true)
	List<Picture> findByProductId(Long productId);
}
