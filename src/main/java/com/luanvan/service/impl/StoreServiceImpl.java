package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Store;
import com.luanvan.repo.StoreRepository;
import com.luanvan.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
	
	private StoreRepository StoreRepository;
	
	@Autowired
	public StoreServiceImpl(StoreRepository StoreRepository) {
		this.StoreRepository = StoreRepository;
	}
	
	@Override
	public List<Store> findAll() {
		return StoreRepository.findAll();
	}

	@Override
	public List<Store> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store findById(Long id) {
		return StoreRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public Store create(Store Store) {
		return StoreRepository.save(Store);
	}

	@Override
	public Store update(Store Store, Long id) {
		StoreRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return StoreRepository.save(Store);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
