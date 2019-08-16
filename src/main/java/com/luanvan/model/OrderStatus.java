package com.luanvan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "order_status")
@Data
public class OrderStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String derciption;
	
	//OneToMany OrderStatusDetail
	@OneToMany(mappedBy = "orderStatus")
	private List<OrderStatusDetail> orderStatusDetails;
	
}
