package com.luanvan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.luanvan.dto.response.PromotionDTO;
import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Promotion;
import com.luanvan.model.Store;
import com.luanvan.repo.PromotionRepository;
import com.luanvan.repo.StoreRepository;
import com.luanvan.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{
	
	private PromotionRepository promotionRepository;
	private StoreRepository storeRepository;
	
	@Autowired
	public PromotionServiceImpl(PromotionRepository PromotionRepository, StoreRepository storeRepository) {
		this.promotionRepository = PromotionRepository;
		this.storeRepository = storeRepository;
	}
	
	@Override
	public List<Promotion> findAll() {
		return promotionRepository.findAll();
	}

	@Override
	public List<Promotion> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO findById(Long id) {
		Promotion promotion = promotionRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		ModelMapper modelMapper = new ModelMapper();
 		PromotionDTO promotiondto = modelMapper.map(promotion, PromotionDTO.class);
		return promotiondto;
	}

	@Override
	@Transactional
	public Promotion create(Promotion promotion, Authentication auth){
		
		Long store = storeRepository.findByUserEmail(auth.getName()).getId();
		promotion.setStoreId(store);
		
		return promotionRepository.save(promotion);
	}

	@Override
	public Promotion update(Promotion promotion, Long id) {
		promotionRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return promotionRepository.save(promotion);
	}

	@Override
	public void delete(Long id) {
		promotionRepository.deleteById(id);
	}

	@Override
	public List<Promotion> findByStore(Authentication auth) {
		Long storeId = storeRepository.findByUserEmail(auth.getName()).getId();
		return promotionRepository.findByStoreId(storeId);
	}

}
