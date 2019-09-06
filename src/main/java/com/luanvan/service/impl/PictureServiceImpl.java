package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Picture;
import com.luanvan.repo.PictureRepository;
import com.luanvan.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	
	private PictureRepository pictureRepository;
	
	@Autowired
	public PictureServiceImpl(PictureRepository pictureRepository) {
		this.pictureRepository = pictureRepository;
	}
	
	@Override
	public Picture findById(Long id) {
		return pictureRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Picture> findByProductId(Long productId) {
		return pictureRepository.findByProductId(productId);
	}

	@Override
	public Picture save(Picture picture) {
		return pictureRepository.save(picture);
	}

	@Override
	public void delete(Long id) {
		pictureRepository.deleteById(id);
	}

	@Override
	public Picture findByProductIdAndAvatar(Long productId, boolean avatar) {
		return pictureRepository.findByProductIdAndAvatar(productId, true);
	}

}
