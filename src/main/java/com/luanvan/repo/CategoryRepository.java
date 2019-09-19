package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findByParentId (Long parentid);
	
	List<Category> findByNameContaining(String name);
	
	List<Category> findByStatus(Boolean status);
	
//	@Query(value = "UPDATE category SET status = 0 WHERE id = ?1", nativeQuery = true)
//	void delete(Long id);
}
