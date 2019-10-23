package com.luanvan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "picture")
@AllArgsConstructor
@NoArgsConstructor
@Data
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Picture{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String path;
	
	@NotNull
	private boolean avatar;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
}
