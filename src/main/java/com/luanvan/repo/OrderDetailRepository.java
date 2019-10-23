package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvan.dto.response.ChartOrderInf;
import com.luanvan.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	
	List<OrderDetail> findByOrderStoreId(Long storeId);
	
	@Query(value = "SELECT day(orders.created_at) as created_at, sum(( price.unit_price*(( 100-ifnull(promotion.sale_off,0) )/100) )*order_detail.quantity) as total FROM order_detail \r\n" + 
			"join orders on order_detail.order_id = orders.id\r\n" + 
			"left join promotion on order_detail.promotion_id = promotion.id\r\n" + 
			"join price on order_detail.price_id = price.id\r\n" + 
			"where orders.order_status_id = 5 \r\n" + 
			"and orders.store_id = ?1 \r\n" + 
			"and Year(orders.created_at) = ?2 \r\n" + 
			"and Month(orders.created_at) = ?3 \r\n" + 
			"group by day(orders.created_at)", nativeQuery = true)
	List<ChartOrderInf> chartOfMonth(Long storeId,int year,int month);
	
	
	@Query(value = "SELECT month(orders.created_at) created_at, sum(( price.unit_price*(( 100-ifnull(promotion.sale_off,0) )/100) )*order_detail.quantity) as total FROM order_detail \r\n" + 
			"join orders on order_detail.order_id = orders.id\r\n" + 
			"left join promotion on order_detail.promotion_id = promotion.id\r\n" + 
			"join price on order_detail.price_id = price.id\r\n" + 
			"where orders.order_status_id = 5\r\n" + 
			"and orders.store_id = ?1\r\n" + 
			"and Year(orders.created_at) = ?2\r\n" + 
			"group by month(orders.created_at)", nativeQuery = true)
	List<ChartOrderInf> chartOfYear(Long storeId, int year);
	
	
	@Query(value="SELECT day(orders.created_at) created_at, sum(( price.unit_price*(( 100-ifnull(promotion.sale_off,0) )/100) )*order_detail.quantity) as total FROM order_detail \r\n" + 
			"join orders on order_detail.order_id = orders.id\r\n" + 
			"left join promotion on order_detail.promotion_id = promotion.id\r\n" + 
			"join price on order_detail.price_id = price.id\r\n" + 
			"where orders.order_status_id = 5\r\n" + 
			"and orders.store_id = ?1", nativeQuery = true)
	List<ChartOrderInf> tongDoanhThu(Long storeId);
	
}
