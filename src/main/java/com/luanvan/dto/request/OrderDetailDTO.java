package com.luanvan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
	
	@Getter @Setter private Product product;
	@Getter @Setter private int quantity;
	@Getter @Setter private int amount;
	
	@Getter
    @Setter
    public static class Product {
        private Long id;
        private String name;
    }
}
