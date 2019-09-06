package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Producer;
import com.luanvan.repo.ProducerRepository;
import com.luanvan.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService{
	
	private ProducerRepository producerRepository;
	
	@Autowired
	public ProducerServiceImpl(ProducerRepository producerRepository) {
		this.producerRepository = producerRepository;
	}
	
	@Override
	public List<Producer> findAll() {
		return producerRepository.findAll();
	}

	@Override
	public List<Producer> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producer findById(Long id) {
		return producerRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public Producer create(Producer producer) {
		return producerRepository.save(producer);
	}

	@Override
	public Producer update(Producer producer, Long id) {
		producerRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return producerRepository.save(producer);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
