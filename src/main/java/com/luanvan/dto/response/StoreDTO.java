package com.luanvan.dto.response;

import java.util.Date;

import com.luanvan.model.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StoreDTO {
	private Long id;
	private String name;
	private int status;
	private String address;
	private String phoneNumber;
	private String derciption;
	private Date createdAt;
	private User user;
	
	@Getter @Setter
	public static class User{
		private Long id;
		private String email;
		private Customer customer;
	}
	

}
