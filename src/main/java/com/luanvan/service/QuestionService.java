package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Question;

public interface QuestionService {
	//tim 1 theo id
	Question findById(Long id);
	
	//tim list theo product id
	List<Question> findByProductId(Long id);
	
	//them va sua
	Question save(Question question);
	
	//xoa
	void delete(Long id);
	
}
