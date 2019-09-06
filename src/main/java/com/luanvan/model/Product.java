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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pictures")
@EqualsAndHashCode(exclude = "pictures")
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Material material;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Origin origin;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Store store;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Producer producer;
	
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<Picture> pictures;
	
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<Price> prices;
	
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<Question> questions;
	
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<Review> reviews;
	
//	@ManyToMany(cascade = {CascadeType.MERGE})
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@JoinTable(name = "attribute_value_product",
//			joinColumns = @JoinColumn(name = "product_id"),
//			inverseJoinColumns = @JoinColumn(name = "attribute_value_id")
//	)
//	private Set<AttributeValue> attributeValues = new HashSet<>();
	
//	@OneToMany(mappedBy = "product")
//	private List<PromotionProduct> promotionProducts;
}
