package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.request.CreateQuestion;
import com.luanvan.dto.response.QuestionDTO;
import com.luanvan.model.CustomUserDetails;

public interface QuestionService {
	//tim 1 theo id
	QuestionDTO findById(Long id);
	
	//tim list theo product id
	List<QuestionDTO> findByProductId(Long id);
	
	//them va sua
	void save(CreateQuestion question, CustomUserDetails user);
	
	//xoa
	void delete(Long id);

	List<QuestionDTO> findByStore(Long storeId);
	
}
