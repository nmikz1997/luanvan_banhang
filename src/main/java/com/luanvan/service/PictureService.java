package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Picture;

public interface PictureService {
	//tim 1 anh theo id
	Picture findById(Long id);
	
	//tim list anh theo product id
	List<Picture> findByProductId(Long id);
	
	//tim avatar product
	Picture findByProductIdAndAvatar(Long productId,boolean avatar);
	
	//them va sua anh
	Picture save(Picture picture);
	
	//xoa anh
	void delete(Long id);
	
}
