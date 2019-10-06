package com.luanvan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//ngay tao
	@CreatedDate
	private Date createAt;
	
	//ngay ap dung
	private Date dateStart;
	
	//ngay ket thuc
	private Date dateEnd;
	
	//tinh trang
	private boolean status;
	
	//ManyToOne Store
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
	
	//ManyToOne MemberType
	@ManyToOne
	@JoinColumn(name = "member_type_id")
	private MemberType memberType;
}
