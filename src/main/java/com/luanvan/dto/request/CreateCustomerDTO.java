package com.luanvan.dto.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luanvan.model.Customer;
import com.luanvan.model.Order;
import com.luanvan.model.Review;
import com.luanvan.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO {
	
	@NotBlank(message="Vui lòng nhập họ tên")
	private String name;
	
	private boolean gender;
	
	@Past(message="ngày sinh không hợp lệ")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date birthDate;
	
	@NotBlank(message="Vui lòng nhập số điện thoại")
	@Pattern(regexp = "(03[2-9]|05[6|8|9]|07[0|6-9]|08[1-9]|09[^5])+([0-9]{7})\\b", message="Sai định dạng số điện thoại")
	private String phoneNumber;
}
