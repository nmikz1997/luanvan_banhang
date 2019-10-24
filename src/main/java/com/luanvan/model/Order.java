package com.luanvan.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "orders")
@Table(name = "Orders")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
	private String address;
	
//	@NotNull
//	private String phoneNumber;
	
	@CreatedDate
	@Column(updatable = false)
	private Date createdAt;
	
	@LastModifiedDate 
	private Date updatedAt;
	
	public Integer getTotal() {
		Integer total = 0;
        List<OrderDetail> orderdetails = getOrdersDetail();
        
        for (OrderDetail dt : orderdetails) {
        	total += dt.getAmount();
        }
        return total;    
	}
	
	//OneToMany OrderDetail
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> ordersDetail;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentType paymentType;
	
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name = "order_status_id")
	private OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "order_group_id")
	private OrderGroup orderGroup;
}
