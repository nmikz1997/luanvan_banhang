package com.luanvan.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
	
	@NotBlank(message = "Vui lòng nhập email")
	@Email(message = "email không hợp lệ")
	private String email;
	
	@NotBlank(message = "Vui lòng nhập password")
	@Length(min = 8, max = 32, message="mật khẩu độ dài phải từ 8-32 kí tự")
	private String password;
}
