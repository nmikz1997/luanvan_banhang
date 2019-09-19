package com.luanvan.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvan.dto.response.ProductDTO;
import com.luanvan.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	//select
	@Query(value ="SELECT * FROM PRODUCT", nativeQuery = true)
	List<ProductDTO> selectAll();
	
	@Query(value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
	List<Product> findByCategory(Long categoryId);
	
	@Query(value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
	List<Product> findByMaterial(Long MaterialId);
	
	@Query(value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
	List<Product> findByOrigin(Long OriginId);
	
	@Query(value = "SELECT * FROM PRODUCT WHERE store_id = ?1", nativeQuery = true)
	List<Product> findByStore(Long storeId);

	List<Product> findByNameIgnoreCaseContaining(String name); //tìm theo tên
	
	
	// Soft Delete
	
	@Modifying
	@Query(value = "UPDATE product SET status = 0 WHERE category_id = ?1", nativeQuery = true)
	void deleteByCategoryId(Long categoryId);
	
	@Modifying
	@Query(value = "UPDATE product SET status = 0 WHERE origin_id 	= ?1", nativeQuery = true)
	void deleteByOriginId(Long originId);
	
	@Modifying
	@Query(value = "UPDATE product SET status = 0 WHERE material_id = ?1", nativeQuery = true)
	void deleteByMaterialId(Long materialId);
	
	//Phan trang
	
	//List<Product> findAllByNameLike(String name, Pageable pageable);
	
	//tim san pham het khuyen mai
	// where x.dayEndDate < today
	//List<Product> findByPromotionsDayEndBefore(Date today);
	
	//san pham dang khuyen mai
	//List<AnEntity> findByStartDateBeforeAndEndDateAfter(Date startDate, Date endDate);
	//List<Product> findByPromotionsDayStartBeforeAndPromotionsDayEndAfter(Date today,Date toDay);
	
	//sản pham moi nhat
	List<Product> findAllByOrderByIdDesc();
	
	//sản phẩm theo của hàng
	List<Product> findByStoreId(Long storeId);
	
	//tìm sản phẩm theo mảng id sản phẩm
	List<Product> findByIdIn(List<Long> ids);
	
	//tất cả sản phẩm còn hàng và có khuyến mãi sẽ bị lặp record do join
	//findProductsByQuantityGreaterThanAndPromotionsIdNotNull
	//List<Product> findProductsByQuantityGreaterThanAndPromotionsIdNotNull(Long soluong);
	
}
