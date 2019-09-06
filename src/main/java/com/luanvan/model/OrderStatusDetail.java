package com.luanvan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "order_status_detail")
@Data
@EntityListeners(AuditingEntityListener.class)
public class OrderStatusDetail implements Serializable{
	
	private static final long serialVersionUID = -2818982875581539085L;

	@Id
	@ManyToOne
	@JoinColumn(name = "order_status_id")
	private OrderStatus orderStatus;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	//ngay tao
	@CreatedDate
	private Date createdAt;
}
