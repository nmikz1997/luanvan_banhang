package com.luanvan.dto.response;

import lombok.Getter;
import lombok.Setter;

public class OrderDetailDTO {
	@Getter @Setter private Product product;
	@Getter @Setter private Price price;
	@Getter @Setter private Promotion promotion;
	@Getter @Setter private int quantity;
	
	@Getter
    @Setter
    public static class Product {
        private Long id;
        private String name;
        private String avatar;
    }
	
	@Getter
    @Setter
    public static class Price {
        private Long id;
        private int unitPrice;
    }
	
	@Getter
    @Setter
    public static class Promotion {
        private Long id;
        private int saleOff;
    }
}
