package com.luanvan.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//@Entity
//@Table(name = "role")
//@Setter
//@Getter
public class Role {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;
	
//	@ManyToMany(mappedBy = "roles")
//	private Set<User> users;
	
	public Role() {
		
	}

	public Role(String name) {
		super();
		this.name = name;
	}
}
