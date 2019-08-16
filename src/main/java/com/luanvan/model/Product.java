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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
@Data // lombok giúp generate các hàm constructor, get, set
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String derciption;
	
	@NotNull
	private int status;
	
	@NotNull
	private int price;
	
	@NotNull
	private Long quantity;
	
	@CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "origin_id")
	private Origin origin;
	
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
	
	@OneToMany(mappedBy = "product")
	private List<Image> images;
	
	@OneToMany(mappedBy = "product")
	private List<UnitPrice> unitPrice;
	
	@OneToMany(mappedBy = "product")
	private List<PromotionProduct> promotionProducts;

}
