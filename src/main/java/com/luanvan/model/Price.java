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

@Data
@Entity(name="price")
@Table(name = "price")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Price{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int unitPrice;
	
	@NotNull
	@Column(name = "root", columnDefinition = "tinyInt(1) default 0")
	private int root;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
	
	@JsonIgnore
	@OneToMany(mappedBy="price")
	private List<OrderDetail> orderdetails;
}
