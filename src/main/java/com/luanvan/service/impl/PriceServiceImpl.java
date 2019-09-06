package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.Price;
import com.luanvan.repo.PriceRepository;
import com.luanvan.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	
	private PriceRepository priceRepository;
	
	@Autowired
	public PriceServiceImpl(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Price save(Price price) {
		return priceRepository.save(price);
	}

	@Override
	public List<Price> findByProduct(Long productId) {
		return priceRepository.findPriceByProductId(productId);
	}

}
