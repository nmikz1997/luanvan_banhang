package com.luanvan.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product{

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
	
	private int priceNew;
	
	private int avgStar;
	
	@NotNull
	private String avatar;
	
	@NotNull
	private Long quantity;
	
	@CreatedDate
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date createdAt;
	
	@LastModifiedDate
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
	
	
	@ManyToMany
    @JoinTable(
            name = "product_attribute_value",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id")
    )
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
    private Set<AttributeValue> attributeValues;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
    private List<Promotion> promotions;

	
//	@OneToMany(mappedBy = "product")
//	private List<PromotionProduct> promotionProducts;
}
