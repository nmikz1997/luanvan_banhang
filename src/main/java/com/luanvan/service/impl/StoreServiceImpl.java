package com.luanvan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.dto.request.CreateStoreDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.StoreDTO;
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
	public List<StoreDTO> findAll() {
		List<Store> stores = StoreRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		List<StoreDTO> dto = mapper.map(stores,new TypeToken<List<StoreDTO>>(){}.getType());
		return dto;
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
	@Transactional
	public void create(CreateStoreDTO storeDTO) {
		ModelMapper mapper = new ModelMapper();
		Store store = mapper.map(storeDTO, Store.class);
		StoreRepository.save(store);
	}

	@Override
	public Store update(Store Store, Long id) {
		StoreRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return StoreRepository.save(Store);
	}
	
	@Override
	public void updateStatus(Store status, Long id) {
		Store store = StoreRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		store.setStatus(status.getStatus());
		StoreRepository.save(store);
	}

	@Override
	public void delete(Long id) {
		StoreRepository.deleteById(id);
	}

	

}
