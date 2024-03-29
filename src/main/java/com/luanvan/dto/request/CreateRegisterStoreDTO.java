package com.luanvan.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.luanvan.model.District;
import com.luanvan.model.Province;
import com.luanvan.model.Ward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRegisterStoreDTO {
	
	@Valid
	private CreateUserDTO user;
	
	@Valid 
	private StoreDTO store;
	
	@Valid 
	private CreateCustomerDTO customer;
	
	@Setter @Getter
	public static class StoreDTO {
		
		@NotBlank(message="Vui lòng nhập tên cửa hàng")
		private String name;
		
		@NotBlank(message="Vui lòng nhập địa chỉ")
		private String address;
		
		@NotBlank(message="Vui lòng nhập số điện thoại")
		@Pattern(regexp = "(03[2-9]|05[6|8|9]|07[0|6-9]|08[1-9]|09[^5])+([0-9]{7})\\b", message="Sai định dạng số điện thoại")
		private String phoneNumber;
		
		private String derciption;
		
	    private Province province;
		
	    private District district;

	    private Ward ward;
	}
}
