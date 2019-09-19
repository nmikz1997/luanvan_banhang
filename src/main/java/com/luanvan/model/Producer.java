package com.luanvan.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Boolean status;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String phoneNumber;
	
	@URL
	private String website;
	
	@JsonIgnore
	@OneToMany(mappedBy="producer")
	private List<Product> product;
}
