package com.luanvan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name = "district")
public class District {
	@Id
	@Column(name = "districtid")
	private String districtid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "provinceid")
	private String provinceid;

}
