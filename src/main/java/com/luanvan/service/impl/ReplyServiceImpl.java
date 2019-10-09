package com.luanvan.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.dto.request.CreateReply;
import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Question;
import com.luanvan.model.Reply;
import com.luanvan.model.Store;
import com.luanvan.repo.QuestionRepository;
import com.luanvan.repo.ReplyRepository;
import com.luanvan.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	private ReplyRepository replyRepository;
	private QuestionRepository questionRepository;
	
	@Autowired
	public ReplyServiceImpl(ReplyRepository replyRepository,
			QuestionRepository questionRepository) {
		this.replyRepository = replyRepository;
		this.questionRepository = questionRepository;
	}

	
	@Override
	public Reply findById(Long id) {
		return replyRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Reply> findByQuestion(Long productId) {
		return replyRepository.findByQuestionId(productId);
	}

	@Override
	public void save(CreateReply replydto, Store store) {
		
		ModelMapper mapper = new ModelMapper();
		Reply reply = mapper.map(replydto, Reply.class);
		reply.setStore(store);
		reply.setStatus(true);
		
		Question question = questionRepository.getOne(replydto.getQuestion().getId());
		question.setStatus(true);
		
		questionRepository.save(question);
		replyRepository.save(reply);
	}

	@Override
	public void delete(Long id) {
		replyRepository.deleteById(id);
	}

}
