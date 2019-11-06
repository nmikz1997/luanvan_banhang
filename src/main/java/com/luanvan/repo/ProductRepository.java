package com.luanvan.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.ProductSearchDTO;
import com.luanvan.dto.response.TopSeller;
import com.luanvan.model.Category;
import com.luanvan.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
	/*@Query(value ="SELECT * FROM PRODUCT INNER JOIN PRICE ON PRODUCT.id = PRICE.product_id GROUP BY PRODUCT.id HAVING MAX(PRICE.created_at)",
			countQuery = "SELECT count(*) FROM PRODUCT INNER JOIN PRICE ON PRODUCT.id = PRICE.product_id GROUP BY PRODUCT.id",
			nativeQuery = true)*/
	@Query(value = "SELECT pro FROM product pro JOIN pro.prices pri "+
			"LEFT JOIN pro.ordersDetails dt "+
			"WHERE pro.plug LIKE ?1 AND pro.status = 1"		+
			"AND CAST( pro.material.id AS string )  LIKE ?2 " 	+ 
			"AND CAST( pro.origin.id AS string ) 	LIKE ?3 " 	+ 
			"AND CAST( pro.producer.id AS string ) 	LIKE ?4 " 	+
			"AND CAST( pro.store.id AS string ) 	LIKE ?5 " 	+
			"AND pro.avgStar >= ?6 "		+
			"AND pro.category.id IN (SELECT cate.id FROM category cate where cate.parentId = ?7 or cate.id = ?7) "+
			"GROUP BY pro.id")
	Page<Product> pageProducts(
			String name,
			String material,
			String origin, 
			String producer,
			String store,
			float ratting,
			Long cate,
			Pageable pageable
			);
	
	@Query(value = "SELECT pro FROM product pro JOIN pro.prices pri "+
			"LEFT JOIN pro.ordersDetails dt "+
			"WHERE pro.plug LIKE ?1 AND pro.status = 1"		+
			"AND CAST( pro.material.id AS string )  LIKE ?2 " 	+ 
			"AND CAST( pro.origin.id AS string ) 	LIKE ?3 " 	+ 
			"AND CAST( pro.producer.id AS string ) 	LIKE ?4 " 	+
			"AND CAST( pro.store.id AS string ) 	LIKE ?5 " 	+
			"AND pro.avgStar >= ?6 "		+
			"GROUP BY pro.id")
	Page<Product> pageProducts(
			String name,
			String material,
			String origin, 
			String producer,
			String store,
			float ratting,
			Pageable pageable
			);
	
	Page<ProductSearchDTO> findByPlug(String plug, Pageable pageable);
	
	//select
	@Query(value ="SELECT * FROM PRODUCT", nativeQuery = true)
	List<ProductDTO> selectAll();
	
	@Query(value="select product_id from order_detail group by product_id order by count(product_id) desc limit ?1", nativeQuery = true)
	List<TopSeller> bestSeller(int limit);
	
//	@Query(value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
//	List<Product> findByCategory(Long categoryId);
	
	@Query(value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
	List<Product> findByMaterial(Long MaterialId);
	
	@Query(value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
	List<Product> findByOrigin(Long OriginId);
	
	@Query(value = "SELECT * FROM PRODUCT WHERE store_id = ?1", nativeQuery = true)
	List<Product> findByStore(Long storeId);

	List<Product> findByNameIgnoreCaseContaining(String name); //tìm theo tên
	
	
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
	List<Product> findFirst12ByStatusOrderByIdDesc(int status);
	
	//Sản phẩm theo category
	List<Product> findByCategoryIn(List<Category> categories);
	
	//sản phẩm theo của hàng
	List<Product> findByStoreId(Long storeId);
	
	//tìm sản phẩm theo mảng id sản phẩm
	List<Product> findByIdInAndStatus(List<Long> ids,int status);
	
	//tim san pham theo ten khong dau
	
	//tất cả sản phẩm còn hàng và có khuyến mãi sẽ bị lặp record do join
	//findProductsByQuantityGreaterThanAndPromotionsIdNotNull
	//List<Product> findProductsByQuantityGreaterThanAndPromotionsIdNotNull(Long soluong);
}
