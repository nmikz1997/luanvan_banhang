package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Reply;
import com.luanvan.repo.ReplyRepository;
import com.luanvan.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	private ReplyRepository replyRepository;
	
	@Autowired
	public ReplyServiceImpl(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
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
	public Reply save(Reply reply) {
		return replyRepository.save(reply);
	}

	@Override
	public void delete(Long id) {
		replyRepository.deleteById(id);
	}

}
