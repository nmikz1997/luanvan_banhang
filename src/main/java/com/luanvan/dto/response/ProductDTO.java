package com.luanvan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Long id;
	private String name;
	private String picture;
//	private Integer star;
	private Integer priceOld;
	private Integer priceNew;
//	private Integer saleOff;
		
}
