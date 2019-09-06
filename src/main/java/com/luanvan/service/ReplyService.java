package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Reply;

public interface ReplyService {
	//tim 1 theo id
	Reply findById(Long id);
	
	//tim reply list theo question
	List<Reply> findByQuestion(Long questionId);
	
	//them va sua
	Reply save(Reply reply);
	
	//xoa
	void delete(Long id);
	
}
