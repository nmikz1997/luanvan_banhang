package com.luanvan.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	//ten
	@NotNull
	private String name;
	
	//gioitinh
	private boolean gender;
	
	//ngaysinh
	@Past
	private Date birthDate;
	
	//sdt
	private String phoneNumber;
	
	//OneToOne user
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Review> reviews;
	
}