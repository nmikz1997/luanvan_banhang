package com.luanvan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CreateQuestion {
	@Setter @Getter private String topic;
	@Setter @Getter private Product product;
	
	@Setter @Getter
	public static class Product{
		private Long id;
	}
}
