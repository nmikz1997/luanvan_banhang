package com.luanvan.dto.request;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
	
	@Valid
	private CreateUserDTO user;
	
	@Valid 
	private CreateCustomerDTO customer;
}
