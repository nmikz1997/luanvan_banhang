package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Material;
import com.luanvan.repo.MaterialRepository;
import com.luanvan.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService{
	
	private MaterialRepository materialRepository;
	
	@Autowired
	public MaterialServiceImpl(MaterialRepository materialRepository) {
		this.materialRepository = materialRepository;
	}
	
	@Override
	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	@Override
	public List<Material> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Material findById(Long id) {
		return materialRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public Material create(Material material) {
		return materialRepository.save(material);
	}

	@Override
	public Material update(Material material, Long id) {
		materialRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return materialRepository.save(material);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
