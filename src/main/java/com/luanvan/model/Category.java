package com.luanvan.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String plug;
	
	private boolean status;
	
	private Long parentId;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<Product> product;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
    private Set<Attribute> attributes;

}
