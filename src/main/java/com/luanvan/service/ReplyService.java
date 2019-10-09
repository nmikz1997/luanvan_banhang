package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.request.CreateReply;
import com.luanvan.model.Reply;
import com.luanvan.model.Store;

public interface ReplyService {
	//tim 1 theo id
	Reply findById(Long id);
	
	//tim reply list theo question
	List<Reply> findByQuestion(Long questionId);
	
	//them va sua
	void save(CreateReply reply, Store store);
	
	//xoa
	void delete(Long id);
	
}
