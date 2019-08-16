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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Customer {
	//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	//ten
	@NotNull
	private String name;
	
	//gioitinh
	@NotNull
	private int gender;
	
	//ngaysinh
	@Past
	private Date birthDate;
	
	//sdt
	@Size(min = 10, max = 10)
	private String phoneNumber;
	
	//email
	@Email
	private String email;
	
	//OneToOne user
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
}