package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.PaymentType;
import com.luanvan.repo.PaymentTypeRepository;
import com.luanvan.service.PaymentTypeService;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService{
	
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	public PaymentTypeServiceImpl(PaymentTypeRepository paymentTypeRepository) {
		this.paymentTypeRepository = paymentTypeRepository;
	}
	
	@Override
	public List<PaymentType> findAll() {
		return paymentTypeRepository.findAll();
	}

	@Override
	public List<PaymentType> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentType findById(Long id) {
		return paymentTypeRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public PaymentType create(PaymentType paymentType) {
		return paymentTypeRepository.save(paymentType);
	}

	@Override
	public PaymentType update(PaymentType paymentType, Long id) {
		paymentTypeRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return paymentTypeRepository.save(paymentType);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
