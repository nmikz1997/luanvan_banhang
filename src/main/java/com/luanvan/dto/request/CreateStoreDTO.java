package com.luanvan.dto.request;

import lombok.Getter;
import lombok.Setter;

public class CreateStoreDTO {
	@Setter @Getter private Long id;
	@Setter @Getter private String name;
	@Setter @Getter private String address;
	@Setter @Getter private String phoneNumber;
	@Setter @Getter private String email;
	@Setter @Getter private String derciption;
	
	@Setter private User user;
	
	@Setter @Getter
	public static class User {
		private Long id;
		private String email;
	}
}
