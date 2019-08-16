package com.luanvan.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "order_status_detail")
@Data
@EntityListeners(AuditingEntityListener.class)
public class OrderStatusDetail implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name = "order_status_id")
	private OrderStatus orderStatus;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	
	//tinh trang don hang

	
	//ngay tao
	
	//ngay cap nhat
	
	//User cap nhat
}
