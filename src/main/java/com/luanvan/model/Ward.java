package com.luanvan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name = "ward")
public class Ward {
	@Id
	@Column(name = "wardid")
	private String wardid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "districtid")
	private String districtid;

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrictid() {
		return districtid;
	}

	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}
}
