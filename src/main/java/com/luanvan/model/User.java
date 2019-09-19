package com.luanvan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	//OneToOne Custommer
//	@OneToOne(mappedBy = "user")
//	private Customer customer;
//	
	@JsonIgnore
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