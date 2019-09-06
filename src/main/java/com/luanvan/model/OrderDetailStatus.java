package com.luanvan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
//	@NotNull
//	@Column(columnDefinition = "TEXT")
//	private String derciption;
//	
//	//OneToMany OrderStatusDetail
//	@OneToMany(mappedBy = "orderStatus")
//	private List<OrderStatusDetail> orderStatusDetails;
	
}
