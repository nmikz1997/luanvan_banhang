package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.MemberType;
import com.luanvan.repo.MemberTypeRepository;
import com.luanvan.service.MemberTypeService;

@Service
public class MemberTypeServiceImpl implements MemberTypeService{
	
	private MemberTypeRepository MemberTypeRepository;
	
	@Autowired
	public MemberTypeServiceImpl(MemberTypeRepository MemberTypeRepository) {
		this.MemberTypeRepository = MemberTypeRepository;
	}
	
	@Override
	public List<MemberType> findAll() {
		return MemberTypeRepository.findAll();
	}

	@Override
	public List<MemberType> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberType findById(Long id) {
		return MemberTypeRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public MemberType create(MemberType MemberType) {
		return MemberTypeRepository.save(MemberType);
	}

	@Override
	public MemberType update(MemberType MemberType, Long id) {
		MemberTypeRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return MemberTypeRepository.save(MemberType);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}
	
}
