package com.luanvan.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchDTO {
	
	private Long id;
	
	private String name;
	
	private String derciption;
	
	private int status;
	
	private float avgStar;
	
	private String plug;
	
	private String avatar;
	
	private Long quantity;
	
	private Date createdAt;

	private Date updatedAt;
	
}
