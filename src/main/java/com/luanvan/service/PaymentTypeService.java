package com.luanvan.service;

import java.util.List;

import com.luanvan.model.PaymentType;

public interface PaymentTypeService {
	
	List<PaymentType> findAll();
	
	List<PaymentType> findByName(String name);
	
	PaymentType findById(Long id);
	
	PaymentType create(PaymentType paymentType);

	PaymentType update(PaymentType paymentType, Long id);
	
	void delete(Long id);
}
