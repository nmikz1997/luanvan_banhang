package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Origin;
import com.luanvan.repo.OriginRepository;
import com.luanvan.service.OriginService;

@Service
public class OriginServiceImpl implements OriginService{
	
	private OriginRepository originRepository;
	
	@Autowired
	public OriginServiceImpl(OriginRepository originRepository) {
		this.originRepository = originRepository;
	}
	
	@Override
	public List<Origin> findAll() {
		return originRepository.findAll();
	}

	@Override
	public List<Origin> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Origin findById(Long id) {
		return originRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public Origin create(Origin origin) {
		return originRepository.save(origin);
	}

	@Override
	public Origin update(Origin origin, Long id) {
		originRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return originRepository.save(origin);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
