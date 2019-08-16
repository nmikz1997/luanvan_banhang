package com.luanvan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberType {
	//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//ten
	@NotNull
	private String name;
	
	//thoihan
	@NotNull
	private int duration; //thoi han la thang
	
	//mota
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String derciption;
	
	//OneToMany Member
	@OneToMany(mappedBy = "memberType")
	private List<Member> members;
	
}