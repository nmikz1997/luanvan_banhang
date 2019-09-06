package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Question;
import com.luanvan.repo.QuestionRepository;
import com.luanvan.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private QuestionRepository questionRepository;
	
	@Autowired
	public QuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	
	@Override
	public Question findById(Long id) {
		return questionRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Question> findByProductId(Long productId) {
		return questionRepository.findQuestionByProductId(productId);
	}

	@Override
	public Question save(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public void delete(Long id) {
		questionRepository.deleteById(id);
	}

}
