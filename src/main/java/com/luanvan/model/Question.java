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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Question{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String topic;
	
	private boolean status;
	
	@CreatedDate
	@Column(updatable = false)
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;
	
	@JsonIgnore
	@OneToMany(mappedBy="question",cascade = CascadeType.REMOVE)
	private List<Reply> replies;
}
