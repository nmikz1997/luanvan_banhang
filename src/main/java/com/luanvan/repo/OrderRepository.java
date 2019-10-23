package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvan.dto.response.ChartOrderInf;
import com.luanvan.model.Customer;
import com.luanvan.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByStoreId(Long storeId);
	
	List<Order> findByCustomer(Customer customer);
	
	List<Order> findByOrderGroupId(Long id);
	
	@Query(value="select o from orders o where o.store.id = ?1 and o.orderStatus.id = ?2 and Year(o.createdAt) = ?3")
	List<Order> findOrderOfYear(Long storeId, Long statusId,int year);
	
	@Query(value="select o from orders o where o.store.id = ?1 and o.orderStatus.id = ?2 and Year(o.createdAt) = ?3 and Month(o.createdAt) = ?4")
	List<Order> findOrderOfMonth(Long storeId, Long statusId,int year, int month);
	
	@Query(value="select order_status_id as created_at,count(order_status_id) as total from orders\r\n" + 
			"where store_id = ?1\r\n" + 
			"group by order_status_id;", nativeQuery=true)
	List<ChartOrderInf> chartCircle(Long storeId);

}
