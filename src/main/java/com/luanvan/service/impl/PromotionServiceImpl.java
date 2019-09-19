package com.luanvan.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.dto.response.PromotionDTO;
import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Promotion;
import com.luanvan.repo.PromotionRepository;
import com.luanvan.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{
	
	private PromotionRepository promotionRepository;
	
	@Autowired
	public PromotionServiceImpl(PromotionRepository PromotionRepository) {
		this.promotionRepository = PromotionRepository;
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
	public Promotion create(Promotion promotion){
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

}
