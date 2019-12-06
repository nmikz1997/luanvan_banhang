package com.luanvan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "province")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {
	
	@Id
	@Column(name = "provinceid")
	private String provinceid;
	
	@Column(name = "name")
	private String name;
	
}
