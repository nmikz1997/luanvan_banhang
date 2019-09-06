package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Price;

public interface PriceService {	
	//tim gia theo san pham
	List<Price> findByProduct(Long productId);
	
	//them va sua gia
	Price save(Price price);
	
}
