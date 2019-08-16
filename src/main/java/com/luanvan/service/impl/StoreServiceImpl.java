package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.Store;
import com.luanvan.repo.StoreRepository;
import com.luanvan.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
	
	private StoreRepository storeRepository;
	
	@Autowired
	public StoreServiceImpl(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}
	
	@Override
	public List<Store> findAllStore() {
		return storeRepository.findAll();
	}

	@Override
	public Store findStoreById(Long id) {
		return storeRepository.getOne(id);
	}

}
