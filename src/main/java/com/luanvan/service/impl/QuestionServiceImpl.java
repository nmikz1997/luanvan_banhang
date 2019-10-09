package com.luanvan.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.luanvan.dto.request.CreateQuestion;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.QuestionDTO;
import com.luanvan.exception.NotFoundException;
import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Customer;
import com.luanvan.model.Product;
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
	public QuestionDTO findById(Long id) {
		Question question = questionRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		
		ModelMapper mapper = new ModelMapper();
		
		QuestionDTO dto = mapper.map(question, QuestionDTO.class);
		return dto;
	}

	@Override
	public List<QuestionDTO> findByProductId(Long productId) {
		List<Question> questions = questionRepository.findQuestionByProductIdAndStatus(productId, true);
		
		ModelMapper mapper = new ModelMapper();
		List<QuestionDTO> questiondto = mapper.map(questions,new TypeToken<List<QuestionDTO>>(){}.getType());
		
		return questiondto;
	}

	@Override
	public void save(CreateQuestion questiondto, CustomUserDetails user) {
		Product product = new Product();
		Question question = new Question();
		Customer customer = new Customer();
		
		product.setId(questiondto.getProduct().getId());
		customer.setId(user.getCustomerId());
		
		question.setStatus(false);
		question.setProduct(product);
		question.setCustomer(customer);
		question.setTopic(questiondto.getTopic());
		
		questionRepository.save(question);
	}

	@Override
	public void delete(Long id) {
		questionRepository.deleteById(id);
	}

	@Override
	public List<QuestionDTO> findByStore(Long storeId) {
		List<Question> questions = questionRepository.findQuestionByProductStoreId(storeId);
		ModelMapper mapper = new ModelMapper();
		List<QuestionDTO> questiondto = mapper.map(questions,new TypeToken<List<QuestionDTO>>(){}.getType());
		return questiondto;
	}

}
