package com.luanvan.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Promotion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 100)
	private int saleOff;
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String decription;
	
	@CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	@FutureOrPresent
	private Date dayStart;
	
	@Future
	private Date dayEnd;
	
	@OneToMany(mappedBy = "promotion")
	private List<PromotionProduct> promotionProducts;
	
}
