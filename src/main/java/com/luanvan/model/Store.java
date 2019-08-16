package com.luanvan.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "store")
@Getter
@Setter
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Store{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String address;
	
	@NotBlank
	@Size(min = 10, max = 10, message = "Số điện thoại phải 10 ký tự")
	private String phoneNumber;
	
	@NotBlank
	@Email(message = "Email không hợp lệ")
	private String email;
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String derciption;
	
	@AssertTrue
	private boolean status;
	
	@CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "store")
	private List<Product> products;
	
	//OneToMany Member
	@OneToMany(mappedBy = "store")
	private List<Member> members;
	
	//OneToMany order
}
