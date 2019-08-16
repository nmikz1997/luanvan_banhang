package com.luanvan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	//OneToOne Custommer
	@OneToOne(mappedBy = "user")
	private Customer customer;
	
	@OneToOne(mappedBy = "user")
	private Store store;
	
//	@ManyToMany
//	@JoinTable(
//			name = "user_role",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id")
//			)
//	private Set<Role> roles;
}