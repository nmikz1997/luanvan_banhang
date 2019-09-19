package com.luanvan.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "attribute")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String code;
	
	private boolean status;
	
	@JsonIgnoreProperties("attribute")
	@OneToMany(mappedBy="attribute")
	private List<AttributeValue> attributeValues;
	
	
	@ManyToMany()
    @JoinTable(
            name = "category_attribute",
            joinColumns = @JoinColumn(name = "attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
    private Set<Category> categories;
}
