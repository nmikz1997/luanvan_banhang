package com.luanvan.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orders")
@EntityListeners(AuditingEntityListener.class)
@Data// lombok giúp generate các hàm constructor, get, set
@AllArgsConstructor
@NoArgsConstructor
public class Order{
	@Id
	@GeneratedValue
	private Long id;
	
	//dia chi
	@NotNull
	private String address;
	
	//createAt
	@CreatedDate
	private Date createdAt;
	
	//tong gia tri
	@NotNull
	private int total;
	
	//OneToMany OrderStatusDetail
	@OneToMany(mappedBy = "order")
	private List<OrderStatusDetail> orderStatusDetails;
	
	//OneToMany OrderDetail
	
	//ManyToOne Customer
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	//ManyToOne PaymentType
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentType paymentType;
	
}
